/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.projects.project4.model.dao.mysql;

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
import ua.epam.projects.project4.model.dao.DAOFactory;
import ua.epam.projects.project4.model.dao.OrderDAO;
import ua.epam.projects.project4.model.dao.PlantDAO;
import ua.epam.projects.project4.model.entities.Forester;
import ua.epam.projects.project4.model.entities.Order;
import ua.epam.projects.project4.model.entities.Owner;
import ua.epam.projects.project4.model.entities.Plant;
import ua.epam.projects.project4.model.entities.PlantInfo;
import ua.epam.projects.project4.model.entities.Service;
import ua.epam.projects.project4.model.entities.UserInfo;

/**
 * Implementation of OrderDAO for MySQL data base
 *
 * @author Sergiy Tolok
 */
public class MySQLOrder implements OrderDAO {

    /**
     * Connection object
     */
    private Connection connection;
    /**
     * Names of columns in forester table in data base
     */
    public static final String ORDER = "`order`";
    public static final String ORDER_ID = "order.order_id";
    public static final String OWNER_ID = "order.owner_id";
    public static final String FORESTER_ID = "order.forester_id";
    public static final String PLANT_ID = "order.plant_id";
    public static final String SERVICE_ID = "order.service_id";
    public static final String DATE_ADDED = "order.date_added";
    public static final String EXECUTION_DATE = "order.execution_date";
    public static final String CONFIRMATION_DATE = "order.confirmation_date";

    public int insertOrder(Order order) {
        int insertedKey = 0;
        String query;
        try {
            //Create query string
            query = MySQLUtility.createInsertStatment(ORDER, OWNER_ID, FORESTER_ID, PLANT_ID, SERVICE_ID);

            connection = MySqlDAOFactory.getConnection();
            PreparedStatement statment = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statment.setInt(1, order.getOwner().getOwnerId());
            statment.setInt(2, order.getForester().getForesterId());
            //If Plant on order has jast been creating - save plant to data base 
            if (order.getPlant().getPlantId() == null) {
                DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
                PlantDAO plantDAO = daoFactory.getPlantDAO();
                //Set plantId to jast inserted plant
                order.getPlant().setPlantId(plantDAO.insertPlant(order.getPlant()));
            }
            statment.setInt(3, order.getPlant().getPlantId());
            statment.setInt(4, order.getService().getServiceId());

            //Get orderId
            int result = statment.executeUpdate();
            if (result > 0) {
                insertedKey = MySQLUtility.getKey(statment);
            }
            Logger.getLogger(MySQLOrder.class.getName()).info((new Date()).toString() + " " + query);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLOrder.class.getName()).log(Level.ERROR, null, ex);
        }
        return insertedKey;
    }

    public ArrayList<Order> getAllOrders(Owner owner, boolean activeOnly) {
        ArrayList<Order> orders = null;
        String query;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Set in which tables gets data
            String[] tables = new String[]{ORDER, MySQLForester.FORESTER,
                MySQLUserInfo.USER_INFO, MySQLPlant.PLANT};
            //Set conditions list
            LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
            conditions.put(OWNER_ID, "?");
            //If activeOnly is true - load only not confirmed orders
            if (activeOnly) {
                conditions.put(CONFIRMATION_DATE, "NULL");
            }
            conditions.put(MySQLPlant.PLANT_ID, PLANT_ID);
            conditions.put(MySQLForester.FORESTER_ID, FORESTER_ID);
            conditions.put(MySQLForester.USER_INFO_ID, MySQLUserInfo.USER_INFO_ID);

            //Set columns list
            String[] fields = {ORDER_ID, EXECUTION_DATE, DATE_ADDED,
                MySQLForester.FORESTER_ID, MySQLUserInfo.USER_INFO_ID,
                MySQLUserInfo.LOGIN, MySQLUserInfo.FIRST_NAME,
                MySQLUserInfo.SURNAME, MySQLUserInfo.MIDLE_NAME,
                MySQLUserInfo.USER_INFO_ID, MySQLPlant.PLANT_ID,
                MySQLPlant.PLANT_INFO_ID, MySQLPlant.PARK_ID,
                MySQLPlant.PLANTED_DATE, MySQLPlant.CUTED_DATE, SERVICE_ID};

            //Create query string
            query = MySQLUtility.createSelectStatment(tables, conditions, fields);
            PreparedStatement statment = connection.prepareStatement(query);
            statment.setInt(1, owner.getOwnerId());
            ResultSet result = statment.executeQuery();

            //Create result list of orders
            while (result.next()) {
                if (orders == null) {
                    orders = new ArrayList<Order>();
                }
                Order order = new Order();
                order.setOrderId(result.getInt(ORDER_ID));
                order.setExecutionDate(result.getTimestamp(EXECUTION_DATE));
                order.setDateAdded(result.getTimestamp(DATE_ADDED));

                UserInfo userInfo = MySQLUtility.getUserInfo(result);

                Forester forester = new Forester(result.getInt(MySQLForester.FORESTER_ID));
                forester.setUserInfo(userInfo);

                Plant plant = new Plant(result.getInt(MySQLPlant.PLANT_ID));
                plant.setPark(owner.getPark(result.getInt(MySQLPlant.PARK_ID)));
                plant.setPlantedDate(result.getTimestamp(MySQLPlant.PLANTED_DATE));
                plant.setCutedDate(result.getTimestamp(MySQLPlant.CUTED_DATE));
                PlantInfo plantInfo = new PlantInfo(result.getInt(MySQLPlant.PLANT_INFO_ID));

                plant.setPlantInfo(plantInfo);

                Service service = new Service(result.getInt(SERVICE_ID));

                order.setPlant(plant);
                order.setForester(forester);
                order.setOwner(owner);
                order.setService(service);

                orders.add(order);
            }
            Logger.getLogger(MySQLOrder.class.getName()).info((new Date()).toString() + " " + query);
            connection.close();


        } catch (SQLException ex) {
            Logger.getLogger(MySQLOrder.class.getName()).log(Level.ERROR, null, ex);
        }
        return orders;
    }

    public ArrayList<Order> getAllOrders(Forester forester, boolean activeOnly) {
        ArrayList<Order> orders = null;
        String query;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Set in which tables gets data
            String[] tables = new String[]{ORDER, MySQLOwner.OWNER,
                MySQLUserInfo.USER_INFO, MySQLPlant.PLANT};
            //Set conditions list
            LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
            conditions.put(FORESTER_ID, "?");
            //If activeOnly is true - load only not confirmed orders
            if (activeOnly) {
                conditions.put(CONFIRMATION_DATE, "NULL");
            }
            conditions.put(MySQLPlant.PLANT_ID, PLANT_ID);
            conditions.put(MySQLOwner.OWNER_ID, OWNER_ID);
            conditions.put(MySQLOwner.USER_INFO_ID, MySQLUserInfo.USER_INFO_ID);
            //Set columns list
            String[] fields = {ORDER_ID, EXECUTION_DATE, DATE_ADDED,
                MySQLOwner.OWNER_ID, MySQLUserInfo.USER_INFO_ID,
                MySQLUserInfo.LOGIN, MySQLUserInfo.FIRST_NAME,
                MySQLUserInfo.SURNAME, MySQLUserInfo.MIDLE_NAME, MySQLUserInfo.USER_INFO_ID,
                MySQLPlant.PLANT_ID, MySQLPlant.PLANT_INFO_ID, MySQLPlant.PARK_ID,
                MySQLPlant.PLANTED_DATE, MySQLPlant.CUTED_DATE, SERVICE_ID};
            //Create query string
            query = MySQLUtility.createSelectStatment(tables, conditions, fields);
            PreparedStatement statment = connection.prepareStatement(query);
            statment.setInt(1, forester.getForesterId());
            ResultSet result = statment.executeQuery();
            //Create result list of orders
            while (result.next()) {
                if (orders == null) {
                    orders = new ArrayList<Order>();
                }
                Order order = new Order();
                order.setOrderId(result.getInt(ORDER_ID));
                order.setExecutionDate(result.getTimestamp(EXECUTION_DATE));
                order.setDateAdded(result.getTimestamp(DATE_ADDED));

                UserInfo userInfo = MySQLUtility.getUserInfo(result);

                Owner owner = new Owner(result.getInt(MySQLOwner.OWNER_ID));
                owner.setUserInfo(userInfo);

                Plant plant = new Plant(result.getInt(MySQLPlant.PLANT_ID));
                plant.setPark(forester.getPark(result.getInt(MySQLPlant.PARK_ID)));
                plant.setPlantedDate(result.getTimestamp(MySQLPlant.PLANTED_DATE));
                plant.setCutedDate(result.getTimestamp(MySQLPlant.CUTED_DATE));
                PlantInfo plantInfo = new PlantInfo(result.getInt(MySQLPlant.PLANT_INFO_ID));

                plant.setPlantInfo(plantInfo);

                Service service = new Service(result.getInt(SERVICE_ID));

                order.setPlant(plant);
                order.setForester(forester);
                order.setOwner(owner);
                order.setService(service);

                orders.add(order);
            }
            Logger.getLogger(MySQLOrder.class.getName()).info((new Date()).toString() + " " + query);
            connection.close();


        } catch (SQLException ex) {
            Logger.getLogger(MySQLOrder.class.getName()).log(Level.ERROR, null, ex);
        }
        return orders;
    }

    public boolean updateOrder(Order order) {
        int executeUpdate = 0;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Set conditions list
            LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
            conditions.put(ORDER_ID, "?");
            //Set columns list
            String[] fields = {EXECUTION_DATE, CONFIRMATION_DATE};
            //Create query string
            String query = MySQLUtility.createUpdateStatment(ORDER, conditions, fields);
            PreparedStatement statment = connection.prepareStatement(query);

            statment.setTimestamp(1, MySQLUtility.dateToTimestamp(order.getExecutionDate()));
            statment.setTimestamp(2, MySQLUtility.dateToTimestamp(order.getConfirmDate()));
            //If order is for Planting new plant - update planted date 
            if (order.getPlant().getPlantedDate() == null && order.getService().getName().equalsIgnoreCase(Service.PLANT)) {
                order.getPlant().setPlantedDate(new Date());

                DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
                PlantDAO plantDAO = daoFactory.getPlantDAO();
                plantDAO.updatePlant(order.getPlant());
            }
            //If order is for cut down  plant - update cuted date 
            if (order.getService().getName().equalsIgnoreCase(Service.CUT_DOWN)) {
                if (order.getPlant().getCutedDate() == null) {
                    order.getPlant().setCutedDate(new Date());

                    DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
                    PlantDAO plantDAO = daoFactory.getPlantDAO();
                    plantDAO.updatePlant(order.getPlant());
                }
            }
            statment.setInt(3, order.getOrderId());
            executeUpdate = statment.executeUpdate();
            Logger.getLogger(MySQLOrder.class.getName()).info((new Date()).toString() + " " + query);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLOrder.class.getName()).log(Level.ERROR, null, ex);
        }
        return (executeUpdate > 0) ? true : false;
    }
}
