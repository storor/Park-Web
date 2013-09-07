package ua.tolok.projects.project4.model.dao;

import ua.tolok.projects.project4.model.dao.mysql.MySqlDAOFactory;

/**
 * Factory class which creates special factory depending on the type of 
 * data storage that you want to connect
 * @author Sergiy Tolok
 */
public abstract class DAOFactory {

    public enum DAOType {
        MYSQL
    }

    public abstract ForesterDAO getForesterDAO();
    public abstract OwnerDAO getOwnerDAO();
    public abstract ParkDAO getParkDAO();
    public abstract UserInfoDAO getUserInfoDAO();
    public abstract PlantDAO getPlantDAO();
    public abstract PlantInfoDAO getPlantInfoDAO();
    public abstract OrderDAO getOrderDAO();
    public abstract ServiceDAO getServiceDAO();

    public static DAOFactory getDAOFactory(DAOType factory) {
        switch (factory) {
            case MYSQL:
                return new MySqlDAOFactory();
            default:
                return null;
        }
    }
}
