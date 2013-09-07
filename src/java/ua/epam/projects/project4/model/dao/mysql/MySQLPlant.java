package ua.epam.projects.project4.model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedHashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.epam.projects.project4.model.dao.PlantDAO;
import ua.epam.projects.project4.model.entities.Plant;

/**
 * Implementation of MySQLPlant for MySQL data base
 *
 * @author Sergiy Tolok
 */
public class MySQLPlant implements PlantDAO {

    //Connection object
    private Connection connection;
    /**
     * Name of table
     */
    public static final String PLANT = "plant";
    /**
     * Names of columns in forester table in data base
     */
    public static final String PLANT_ID = "plant.plant_id";
    public static final String PARK_ID = "plant.park_id";
    public static final String PLANTED_DATE = "plant.planted_date";
    public static final String CUTED_DATE = "plant.date_cuted";
    public static final String PLANT_INFO_ID = "plant.plant_info_id";

    public int insertPlant(Plant plant) {
        int insertedKey = 0;
        String query = "";
        try {
            connection = MySqlDAOFactory.getConnection();
            //Create query string
            query = MySQLUtility.createInsertStatment(PLANT, PARK_ID, PLANT_INFO_ID);
            PreparedStatement statment = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statment.setInt(1, plant.getPark().getParkId());
            statment.setInt(2, plant.getPlantInfo().getPlantInfoId());
            int result = statment.executeUpdate();
            if (result > 0) {
                //If query executed successfully - get palntId
                insertedKey = MySQLUtility.getKey(statment);
            }
            //store information about the request in the file log file
            Logger.getLogger(MySQLPlant.class.getName()).info((new Date()).toString() + " " + query);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPlant.class.getName()).error(query + "failure caused by ", ex);
        }
        return insertedKey;
    }

    public boolean updatePlant(Plant plant) {
        int executeUpdate = 0;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Set conditions list
            LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
            conditions.put(PLANT_ID, "?");
            //Set columns list
            String[] fields = {PLANTED_DATE, CUTED_DATE};
            //Create query string
            String query = MySQLUtility.createUpdateStatment(PLANT, conditions, fields);
            PreparedStatement statment = connection.prepareStatement(query);
            statment.setTimestamp(1, MySQLUtility.dateToTimestamp(plant.getPlantedDate()));
            statment.setTimestamp(2, MySQLUtility.dateToTimestamp(plant.getCutedDate()));
            statment.setInt(fields.length + 1, plant.getPlantId());
            executeUpdate = statment.executeUpdate();
            Logger.getLogger(MySQLPlant.class.getName()).info((new Date()).toString() + " " + query);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPlant.class.getName()).log(Level.ERROR, null, ex);
        }
        //Determine whether query executed successfully or no
        return (executeUpdate > 0) ? true : false;
    }
}
