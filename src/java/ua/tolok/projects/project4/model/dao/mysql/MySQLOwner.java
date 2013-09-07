/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import ua.tolok.projects.project4.model.dao.OrderDAO;
import ua.tolok.projects.project4.model.dao.OwnerDAO;
import ua.tolok.projects.project4.model.entities.Order;
import ua.tolok.projects.project4.model.entities.Owner;
import ua.tolok.projects.project4.model.entities.UserInfo;

/**
 * Implementation of OwnerDAO for MySQL data base
 * @author Sergiy Tolok
 */
public class MySQLOwner implements OwnerDAO {

    /**
     * Names of columns in forester table in data base
     */
    public static final String USER_INFO_ID = "owner.user_info_id";
    public static final String OWNER_ID = "owner.owner_id";
    public static final String OWNER = "owner";
    public static final String ORGANISATION = "owner.organisation";
    /**
     * Connection object
     */
    private Connection connection;

    public int insertOwner(Owner owner) {
        int insertedKey = 0;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Create query string
            String query = MySQLUtility.createInsertStatment(OWNER, ORGANISATION, USER_INFO_ID);
            PreparedStatement statment = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statment.setString(1, owner.getOrganisation());
            statment.setInt(2, owner.getUserInfo().getUserInfoId());
            int result = statment.executeUpdate();
            if (result > 0) {
                //If query executed successfully - get ownerId
                insertedKey = MySQLUtility.getKey(statment);
            }
            //store information about the request in the file log file
            Logger.getLogger(MySQLOwner.class.getSimpleName()).info((new Date()).toString() + " " + query);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLOwner.class.getName()).log(Level.ERROR, null, ex);
        }
        return insertedKey;
    }

    public Owner findOwner(UserInfo userInfo) {
        Owner owner = null;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Set in which tables gets data
            String[] tables = new String[]{OWNER};
            //Set conditions list
            LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
            conditions.put(USER_INFO_ID, "?");
            //Set columns list
            String[] fields = {OWNER_ID, ORGANISATION};
            //Create query string
            String query = MySQLUtility.createSelectStatment(tables, conditions, fields);
            PreparedStatement statment = connection.prepareStatement(query);
            statment.setInt(1, userInfo.getUserInfoId());
            ResultSet result = statment.executeQuery();
            if (result.next()) {
                owner = new Owner();
                owner.setOwnerId(result.getInt(OWNER_ID));
                owner.setOrganisation(result.getString(ORGANISATION));
                owner.setUserInfo(userInfo);

                //Load parks
                DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
                MySQLPark parkDAO = (MySQLPark) daoFactory.getParkDAO();
                owner.setParkCollection(parkDAO.findParks(owner));

                //Load orders
                OrderDAO orderDAO = daoFactory.getOrderDAO();
                ArrayList<Order> allOrders = orderDAO.getAllOrders(owner, true);
                owner.setOrderCollection(allOrders);

                //store information about the request in the file log file
                Logger.getLogger(MySQLOwner.class.getName()).info((new Date()).toString() + " " + query);
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLOwner.class.getName()).log(Level.ERROR, null, ex);
        }
        return owner;
    }

    public void extendUserInfo(Owner owner) {
        String query;
        try {
            connection = MySqlDAOFactory.getConnection();
             //Set in which tables gets data
            String[] tables = new String[]{OWNER, MySQLUserInfo.USER_INFO};
            //Set conditions list
            LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
            conditions.put(OWNER_ID, "?");
            conditions.put(MySQLUserInfo.USER_INFO_ID, USER_INFO_ID);
            //Set columns list
            String[] fields = {MySQLUserInfo.USER_INFO_ID, MySQLUserInfo.LOGIN,
                MySQLUserInfo.FIRST_NAME,
                MySQLUserInfo.SURNAME, MySQLUserInfo.MIDLE_NAME,
                MySQLUserInfo.USER_INFO_ID};
            //Create query string
            query = MySQLUtility.createSelectStatment(tables, conditions, fields);
            PreparedStatement statment = connection.prepareStatement(query);
            statment.setInt(1, owner.getOwnerId());
            ResultSet result = statment.executeQuery();

            if (result.next()) {
                owner.setUserInfo(MySQLUtility.getUserInfo(result));
            }
            //store information about the request in the file log file
            Logger.getLogger(MySQLOwner.class.getName()).info((new Date()).toString() + " " + query);

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLOwner.class.getName()).log(Level.ERROR, null, ex);
        }
    }
}
