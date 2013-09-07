package ua.tolok.projects.project4.model.dao.mysql;

import java.sql.*;
import java.util.LinkedHashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.tolok.projects.project4.model.dao.UserInfoDAO;
import ua.tolok.projects.project4.model.entities.UserInfo;

/**
 * Implementation of UserInfoDAO for MySQL data base
 *
 * @author Sergiy Tolok
 */
public class MySQLUserInfo implements UserInfoDAO {

    //Name of table
    public static final String USER_INFO = "user_info";
    /**
     * Names of columns in forester table in data base
     */
    public static final String ROLE = "user_info.role";
    public static final String USER_INFO_ID = "user_info.user_info_id";
    public static final String LOGIN = "user_info.login";
    public static final String PASSWORD = "user_info.password";
    public static final String FIRST_NAME = "user_info.first_name";
    public static final String SURNAME = "user_info.surname";
    public static final String MIDLE_NAME = "user_info.midle_name";
    public static final String REG_DATE = "user_info.reg_date";
    /**
     * Connection object
     */
    Connection connection;

    public MySQLUserInfo() {
    }

    public int insertUserInfo(UserInfo userInfo) {
        int insertedKey = 0;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Create query string
            String query = MySQLUtility.createInsertStatment(USER_INFO, ROLE, LOGIN, PASSWORD, FIRST_NAME, SURNAME, MIDLE_NAME);
            PreparedStatement statment = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statment.setBoolean(1, userInfo.isOwner());
            statment.setString(2, userInfo.getLogin());
            statment.setString(3, userInfo.getPassword());
            statment.setString(4, userInfo.getFirstName());
            statment.setString(5, userInfo.getSurname());
            statment.setString(6, userInfo.getMidleName());
            //execute query
            int result = statment.executeUpdate();
            if (result > 0) {
                //If query executed successfully - get userInfoId
                insertedKey = MySQLUtility.getKey(statment);
            }
            connection.close();
            Logger.getLogger(MySQLUserInfo.class.getName()).info((new java.util.Date()).toString() + " " + query);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserInfo.class.getName()).log(Level.ERROR, null, ex);
        }
        return insertedKey;
    }

    public UserInfo findUserInfo(String login, String password) {
        UserInfo userInfo = null;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Set in which tables gets data
            String[] tables = new String[]{MySQLUserInfo.USER_INFO};
            //Set conditions list
            LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
            conditions.put(LOGIN, "?");
            conditions.put(PASSWORD, "?");
            //Set columns list
            String[] fields = {MySQLUserInfo.USER_INFO_ID, ROLE, MySQLUserInfo.LOGIN,
                MySQLUserInfo.FIRST_NAME,
                MySQLUserInfo.SURNAME, MySQLUserInfo.MIDLE_NAME};
            //Create query string
            String query = MySQLUtility.createSelectStatment(tables, conditions, fields);

            PreparedStatement statment = connection.prepareStatement(query);
            statment.setString(1, login);
            statment.setString(2, password);
            //execute query
            ResultSet result = statment.executeQuery();
            if (result.next()) {
                userInfo = MySQLUtility.getUserInfo(result);
                //In depending of user role - setRole
                userInfo.setRole(((result.getObject(MySQLUserInfo.ROLE) == null)
                        ? null : result.getBoolean(MySQLUserInfo.ROLE)));
            }
            connection.close();
            Logger.getLogger(MySQLUserInfo.class.getName()).info((new java.util.Date()).toString() + " " + query);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserInfo.class.getName()).log(Level.ERROR, null, ex);
        }
        return userInfo;
    }

    public boolean isLoginExist(String login) {
        boolean exist = false;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Set in which tables gets data
            String[] tables = new String[]{MySQLUserInfo.USER_INFO};
            //Set conditions list
            LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
            conditions.put(LOGIN, "?");
            //Set columns list
            String[] fields = {MySQLUserInfo.USER_INFO_ID};
            //Create query string
            String query = MySQLUtility.createSelectStatment(tables, conditions, fields);
            PreparedStatement statment = connection.prepareStatement(query);
            statment.setString(1, login);
            //execute query
            ResultSet result = statment.executeQuery();
            if (result.next()) {
                exist = true;
            }
            connection.close();
            Logger.getLogger(MySQLUserInfo.class.getName()).info((new java.util.Date()).toString() + " " + query);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserInfo.class.getName()).log(Level.ERROR, null, ex);
        }
        return exist;
    }
}
