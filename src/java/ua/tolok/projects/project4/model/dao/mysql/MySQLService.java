package ua.tolok.projects.project4.model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.tolok.projects.project4.model.dao.ServiceDAO;
import ua.tolok.projects.project4.model.entities.Service;

/**
 * Implementation of ServiceDAO for MySQL data base
 * @author Sergiy Tolok
 */
public class MySQLService implements ServiceDAO {

    private Connection connection;
    //Name of table
    public static final String SERVICE = "service";
    /**
     * Names of columns in forester table in data base
     */
    public static final String SERVICE_ID = "service.service_id";
    public static final String NAME_RUS = "service.name_rus";
    public static final String NAME = "service.name";
    public static final String DESCRIPTION = "service.description";
    public static final String DESCRIPTION_RUS = "service.description_rus";

    public int insertService(Service service) {
        int insertedKey = 0;
        String query;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Create query string
            query = MySQLUtility.createInsertStatment(SERVICE, NAME, NAME_RUS, DESCRIPTION, DESCRIPTION_RUS);
            PreparedStatement statment = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statment.setString(1, service.getName());
            statment.setString(2, service.getNameRus());
            statment.setString(3, service.getDescription());
            statment.setString(4, service.getDescriptionRus());

            int result = statment.executeUpdate();
            if (result > 0) {
                //If query executed successfully - get serviceId
                insertedKey = MySQLUtility.getKey(statment);
            }
            Logger.getLogger(MySQLService.class.getName()).info((new Date()).toString() + " " + query);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLService.class.getName()).log(Level.ERROR, null, ex);
        }
        return insertedKey;
    }

    public ArrayList<Service> getAllServices() {
        ArrayList<Service> services = null;
        String query;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Set in which tables gets data
            String[] tables = new String[]{SERVICE};
            //Create query string for getting all columns
            query = MySQLUtility.createSelectStatment(tables, null);
            PreparedStatement statment = connection.prepareStatement(query);
            ResultSet result = statment.executeQuery();
            //Get result services
            while (result.next()) {
                if (services == null) {
                    services = new ArrayList<Service>();
                }
                Service service = new Service();
                service.setServiceId(result.getInt(SERVICE_ID));
                service.setName(result.getString(NAME));
                service.setNameRus(result.getString(NAME_RUS));
                service.setDescription(result.getString(DESCRIPTION));
                service.setDescriptionRus(result.getString(DESCRIPTION_RUS));
                services.add(service);
            }
            Logger.getLogger(MySQLService.class.getName()).info((new Date()).toString() + " " + query);
            connection.close();


        } catch (SQLException ex) {
            Logger.getLogger(MySQLService.class.getName()).log(Level.ERROR, null, ex);
        }
        return services;
    }
}
