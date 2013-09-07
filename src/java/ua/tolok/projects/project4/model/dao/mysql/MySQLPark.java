package ua.tolok.projects.project4.model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import ua.tolok.projects.project4.model.dao.DAOFactory;
import ua.tolok.projects.project4.model.dao.ForesterDAO;
import ua.tolok.projects.project4.model.dao.OwnerDAO;
import ua.tolok.projects.project4.model.dao.ParkDAO;
import ua.tolok.projects.project4.model.entities.Forester;
import ua.tolok.projects.project4.model.entities.Owner;
import ua.tolok.projects.project4.model.entities.Park;
import ua.tolok.projects.project4.model.entities.Plant;
import ua.tolok.projects.project4.model.entities.PlantInfo;

/**
 * Implementation of ParkDAO for MySQL data base
 *
 * @author Sergiy Tolok
 */
public class MySQLPark implements ParkDAO {

    /**
     * Names of columns in forester table in data base
     */
    public static final String PARK = "park";
    public static final String PARK_ID = "park_id";
    public static final String NAME = "park.name";
    public static final String DESCRIPTION = "park.description";
    public static final String OWNER_ID = "park.owner_id";
    public static final String FORESTER_ID = "park.forester_id";
    /**
     * Connection object
     */
    private Connection connection;

    public int insertPark(Park park) {
        String query = null;
        int insertedKey = 0;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Create query string
            query = MySQLUtility.createInsertStatment(PARK, NAME, DESCRIPTION, OWNER_ID, FORESTER_ID);
            PreparedStatement statment = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statment.setString(1, park.getName());
            statment.setString(2, park.getDescription());
            statment.setInt(3, park.getOwner().getOwnerId());
            statment.setInt(4, park.getForester().getForesterId());
            int result = statment.executeUpdate();
            if (result > 0) {
                insertedKey = MySQLUtility.getKey(statment);
            }
            //store information about the request in the file log file
            Logger.getLogger(MySQLPark.class.getName()).info((new Date()).toString() + " " + query + (result > 0 ? " succesful" : " failure"));
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPark.class.getName()).error(query + "failure caused by ", ex);
        }
        return insertedKey;
    }

    public ArrayList<Park> findParks(Owner owner) {
        ArrayList<Park> parks = null;
        String query = "";
        try {
            connection = MySqlDAOFactory.getConnection();
            //Set in which table gets data
            String[] tables = new String[]{PARK};
            //Set conditions list
            LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
            conditions.put(OWNER_ID, "?");
            //Set columns list
            String[] fields = {PARK_ID, NAME, DESCRIPTION, FORESTER_ID};
            //Create query string
            query = MySQLUtility.createSelectStatment(tables, conditions, fields);
            PreparedStatement statment = connection.prepareStatement(query);
            statment.setInt(1, owner.getOwnerId());
            ResultSet result = statment.executeQuery();
            //Get list or parks
            while (result.next()) {
                if (parks == null) {
                    parks = new ArrayList<Park>();
                }
                Park park = new Park();
                park.setParkId(result.getInt(PARK_ID));
                park.setName(result.getString(NAME));
                park.setOwner(owner);
                park.setDescription(result.getString(DESCRIPTION));
                park.setForester(new Forester(result.getInt(FORESTER_ID)));
                parks.add(park);
            }
            //store information about the request in the file log file
            Logger.getLogger(MySQLPark.class.getName()).info((new Date()).toString() + " " + query);
            connection.close();


        } catch (SQLException ex) {
            Logger.getLogger(MySQLPark.class.getName()).error(query + "failure caused becouse  ", ex);
        }
        return parks;
    }

    public ArrayList<Park> findParks(Forester forester) {
        ArrayList<Park> parks = null;
        String query = "";
        try {
            connection = MySqlDAOFactory.getConnection();
            //Set in which tables gets data
            String[] tables = new String[]{PARK};
            //Set conditions list
            LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
            conditions.put(FORESTER_ID, "?");
            //Set columns list
            String[] fields = {PARK_ID, NAME, DESCRIPTION, OWNER_ID};
            //Create query string
            query = MySQLUtility.createSelectStatment(tables, conditions, fields);
            PreparedStatement statment = connection.prepareStatement(query);
            statment.setInt(1, forester.getForesterId());
            ResultSet result = statment.executeQuery();
            //Get list or parks
            while (result.next()) {
                if (parks == null) {
                    parks = new ArrayList<Park>();
                }
                Park park = new Park();
                park.setParkId(result.getInt(PARK_ID));
                park.setName(result.getString(NAME));
                park.setOwner(new Owner(result.getInt(OWNER_ID)));
                park.setDescription(result.getString(DESCRIPTION));
                park.setForester(forester);
                parks.add(park);
            }
            //store information about the request in the file log file
            Logger.getLogger(MySQLPark.class.getName()).info((new Date()).toString() + " " + query);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPark.class.getName()).error(query + "failure caused becouse  ", ex);
        }
        return parks;
    }

    public void extendPark(Park park) {
        String query = "";
        try {
            connection = MySqlDAOFactory.getConnection();
            //Set in which tables gets data
            String[] tables = new String[]{MySQLPlant.PLANT};
            //Set conditions list
            LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
            conditions.put(PARK_ID, "?");
            conditions.put(MySQLPlant.PLANTED_DATE, "NOT NULL");
            conditions.put(MySQLPlant.PARK_ID, PARK_ID);
            //Set columns list
            String[] fields = {MySQLPlant.PLANT_ID, MySQLPlant.PLANT_INFO_ID,
                MySQLPlant.PLANTED_DATE, MySQLPlant.CUTED_DATE};
            //Create query string
            query = MySQLUtility.createSelectStatment(tables, conditions, fields);
            PreparedStatement statment = connection.prepareStatement(query);
            statment.setInt(1, park.getParkId());
            ResultSet result = statment.executeQuery();
            //Get result plants
            ArrayList<Plant> plants = null;
            while (result.next()) {
                if (plants == null) {
                    plants = new ArrayList<Plant>();
                }
                Plant plant = new Plant(result.getInt(MySQLPlant.PLANT_ID));
                plant.setPark(park);
                plant.setPlantInfo(new PlantInfo(result.getInt(MySQLPlant.PLANT_INFO_ID)));
                plant.setPlantedDate(result.getTimestamp(MySQLPlant.PLANTED_DATE));
                plant.setCutedDate(result.getTimestamp(MySQLPlant.CUTED_DATE));
                plants.add(plant);
            }
            park.setPlantCollection(plants);

            DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
            //Extend Information about owner or forester 
            if (park.getOwner().getUserInfo() == null) {
                OwnerDAO ownerDAO = daoFactory.getOwnerDAO();
                ownerDAO.extendUserInfo(park.getOwner());
            }
            if (park.getForester().getUserInfo() == null) {
                ForesterDAO foresterDAO = daoFactory.getForesterDAO();
                foresterDAO.extendUserInfo(park.getForester());
            }
            //store information about the request in the file log file
            Logger.getLogger(MySQLPark.class.getName()).info((new Date()).toString() + " " + query);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPark.class.getName()).error(query + "failure caused becouse  ", ex);
        }
    }
}
