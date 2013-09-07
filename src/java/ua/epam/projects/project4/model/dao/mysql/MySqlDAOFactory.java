package ua.epam.projects.project4.model.dao.mysql;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.epam.projects.project4.model.dao.DAOFactory;
import ua.epam.projects.project4.model.dao.ForesterDAO;
import ua.epam.projects.project4.model.dao.OrderDAO;
import ua.epam.projects.project4.model.dao.OwnerDAO;
import ua.epam.projects.project4.model.dao.ParkDAO;
import ua.epam.projects.project4.model.dao.PlantDAO;
import ua.epam.projects.project4.model.dao.PlantInfoDAO;
import ua.epam.projects.project4.model.dao.ServiceDAO;
import ua.epam.projects.project4.model.dao.UserInfoDAO;

/**
 * Factory to create objects of a data connection to MySQL data base
 * @author Sergiy Tolok
 */
public class MySqlDAOFactory extends DAOFactory {

    public static final String CONTEXT = "java:comp/env/jdbc/parkdb";
    /**
     * Connection pool using Tomcat Connection Pool
     */
    public static DataSource pool;

    public MySqlDAOFactory() {
    }

    /**
     * Create connectin whith data base or getting already created
     * @return Connection instance
     */
    public synchronized static Connection getConnection() {
        //Check if pool is not created
        if (pool == null) {
            try {
                //Create new pool
                Context initContext = new InitialContext();
                pool = (DataSource)initContext.lookup(CONTEXT);
                Logger.getLogger(MySqlDAOFactory.class.getName()).info((new java.util.Date()).toString()+" Pool created");
            } catch (NamingException ex) {
                Logger.getLogger(MySqlDAOFactory.class.getName()).log(Level.ERROR, null, ex);
            } 
        }
        try {
            //Return connection 
            return pool.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDAOFactory.class.getName()).log(Level.ERROR, null, ex);
        }
        return null;
    }

    
    @Override
    public OwnerDAO getOwnerDAO() {
        return new MySQLOwner();
    }

    @Override
    public ParkDAO getParkDAO() {
        return new MySQLPark();
    }

    @Override
    public UserInfoDAO getUserInfoDAO() {
        return new MySQLUserInfo();
    }

    @Override
    public ForesterDAO getForesterDAO() {
        return new MySQLForester();
    }

    @Override
    public PlantDAO getPlantDAO() {
        return new MySQLPlant();
    }

    @Override
    public PlantInfoDAO getPlantInfoDAO() {
        return new MySQLPlantInfo();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new MySQLOrder();
    }

    @Override
    public ServiceDAO getServiceDAO() {
        return new MySQLService();
    }
}
