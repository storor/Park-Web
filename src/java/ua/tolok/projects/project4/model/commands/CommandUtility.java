package ua.tolok.projects.project4.model.commands;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import ua.tolok.projects.project4.model.dao.DAOFactory;
import ua.tolok.projects.project4.model.dao.PlantInfoDAO;
import ua.tolok.projects.project4.model.dao.ServiceDAO;
import ua.tolok.projects.project4.model.entities.PlantInfo;
import ua.tolok.projects.project4.model.entities.Service;

/**
 * Helper class for Commands issues
 * @author Sergiy Tolok
 */
public final class CommandUtility {

    private CommandUtility() {
    }

    /**
     * Get from data storage details about services and plants
     * @param req 
     */
    public static void loadServices(DAOFactory daoFactory, HttpServletRequest req) {
        ServiceDAO serviceDAO = daoFactory.getServiceDAO();
        ArrayList<Service> services = serviceDAO.getAllServices();
        req.getSession().setAttribute(Command.SERVICES, services);
    }
    
    /**
     * Get from data storage details about services and plants
     * @param req 
     */
    public static void loadPlantInfoCollection(DAOFactory daoFactory, HttpServletRequest req) {
        PlantInfoDAO plantInfoDAO = daoFactory.getPlantInfoDAO();
        ArrayList<PlantInfo> plantInfoCollection = plantInfoDAO.getAllPlantInfo();
        req.getSession().setAttribute(Command.PLANT_INFO_COLLECTION, plantInfoCollection);
    }
    /**
     * Getting Service object from list by Name
     * @param services list of services
     * @param name name of type of service
     * @return 
     */
    public static Service getServiseByName(ArrayList<Service> services ,String name){
        for(Service service:services){
            if(service.getName().equalsIgnoreCase(name)){
                return service;
            }
        }
        return null;
    }
}
