package ua.tolok.projects.project4.model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.tolok.projects.project4.model.dao.DAOFactory;
import ua.tolok.projects.project4.model.dao.ForesterDAO;
import ua.tolok.projects.project4.model.dao.OrderDAO;
import ua.tolok.projects.project4.model.entities.Forester;
import ua.tolok.projects.project4.model.entities.Order;
import ua.tolok.projects.project4.model.entities.UserInfo;

/**
 * Implementation of ForesterDAO for MySQL data base
 * @author Sergiy Tolok
 */
public class MySQLForester implements ForesterDAO {

    /**
     * Names of columns in forester table in data base
     */
    public static final String USER_INFO_ID = "forester.user_info_id";
    public static final String FORESTER_ID = "forester.forester_id";
    public static final String FORESTER = "forester";
    public static final String QUALIFICATION = "qualification";
    /**
     * Connection object
     */
    private Connection connection;

    public int insertForester(Forester forester) {
        int insertedKey = 0;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Create query string
            String query = MySQLUtility.createInsertStatment(FORESTER, USER_INFO_ID);
            PreparedStatement statment = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statment.setInt(1, forester.getUserInfo().getUserInfoId());
            //execute query
            int result = statment.executeUpdate();
            if (result > 0) {
                //If query executed successfully - get foresterId
                insertedKey = MySQLUtility.getKey(statment);
            }
            connection.close();
            Logger.getLogger(MySQLForester.class.getName()).info((new Date()).toString() + " " +query);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLForester.class.getName()).log(Level.ERROR, null, ex);
        }
        return insertedKey;
    }

    public ArrayList<Forester> getForesters() {
        ArrayList<Forester> foresters = null;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Set in which tables gets data
            String[] tables = new String[]{FORESTER, MySQLUserInfo.USER_INFO};
            //Set conditions list
            LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
            conditions.put(USER_INFO_ID, MySQLUserInfo.USER_INFO_ID);
            //Set columns list
            String[] fields = {FORESTER_ID, QUALIFICATION,
                MySQLUserInfo.USER_INFO_ID, MySQLUserInfo.LOGIN,
                MySQLUserInfo.FIRST_NAME,
                MySQLUserInfo.SURNAME, MySQLUserInfo.MIDLE_NAME};
            //Create query string
            String query = MySQLUtility.createSelectStatment(tables, conditions, fields);
            PreparedStatement statment = connection.prepareStatement(query);
            //execute query
            ResultSet result = statment.executeQuery();
            while (result.next()) {
                if (foresters == null) {
                    foresters = new ArrayList<Forester>();
                }
                Forester forester = new Forester();
                forester.setForesterId(result.getInt(FORESTER_ID));
                forester.setQualification(result.getInt(QUALIFICATION));

                UserInfo userInfo = MySQLUtility.getUserInfo(result);

                forester.setUserInfo(userInfo);
                foresters.add(forester);
            }
            Logger.getLogger(MySQLForester.class.getName()).info((new Date()).toString() + " " +query);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLForester.class.getName()).log(Level.ERROR, null, ex);
        }
        return foresters;
    }

    public Forester findForester(UserInfo userInfo) {
        Forester forester = null;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Set in which table gets data
            String[] tables = new String[]{FORESTER};
            //Set conditions list
            LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
            conditions.put(USER_INFO_ID, "?");
            //Set columns list
            String[] fields = {FORESTER_ID, QUALIFICATION};
            //Create query string
            String query = MySQLUtility.createSelectStatment(tables, conditions, fields);
            PreparedStatement statment = connection.prepareStatement(query);
            //Set condition
            statment.setInt(1, userInfo.getUserInfoId());
            //execute query
            ResultSet result = statment.executeQuery();
            //Get result list
            if (result.next()) {
                forester = new Forester();
                forester.setForesterId(result.getInt(FORESTER_ID));
                forester.setUserInfo(userInfo);
                forester.setQualification(result.getInt(QUALIFICATION));
                
                //Load parks
                DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
                MySQLPark parkDAO = (MySQLPark) daoFactory.getParkDAO();
                forester.setParkCollection(parkDAO.findParks(forester));
                
                //Load orders
                OrderDAO orderDAO = daoFactory.getOrderDAO();
                ArrayList<Order> allOrders = orderDAO.getAllOrders(forester, true);
                forester.setOrderCollection(allOrders);

            }
            Logger.getLogger(MySQLForester.class.getName()).info((new Date()).toString() + " " + query);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLForester.class.getName()).log(Level.ERROR, null, ex);
        }
        return forester;
    }

    public void extendUserInfo(Forester forester) {
        String query;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Set in which tables gets data
            String[] tables = new String[]{FORESTER, MySQLUserInfo.USER_INFO};
            //Set conditions list
            LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
            conditions.put(FORESTER_ID, "?");
            conditions.put(MySQLUserInfo.USER_INFO_ID, USER_INFO_ID);
            //Set columns list
            String[] fields = {MySQLUserInfo.USER_INFO_ID, MySQLUserInfo.LOGIN,
                MySQLUserInfo.FIRST_NAME,
                MySQLUserInfo.SURNAME, MySQLUserInfo.MIDLE_NAME,
                MySQLUserInfo.USER_INFO_ID};
            //Create query string
            query = MySQLUtility.createSelectStatment(tables, conditions, fields);
            PreparedStatement statment = connection.prepareStatement(query);
            statment.setInt(1, forester.getForesterId());
            //execute query
            ResultSet result = statment.executeQuery();

            if (result.next()) {
                forester.setUserInfo(MySQLUtility.getUserInfo(result));
            }
            //store information about the request in the file log file
            Logger.getLogger(MySQLForester.class.getName()).info((new Date()).toString() + " " + query);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLForester.class.getName()).log(Level.ERROR, null, ex);
        }
    }
}
