package ua.epam.projects.project4.model.commands;

import java.util.ArrayList;
import ua.epam.projects.project4.model.dao.DAOFactory;
import ua.epam.projects.project4.model.entities.Owner;
import ua.epam.projects.project4.model.entities.Park;
import ua.epam.projects.project4.model.entities.PlantInfo;

/**
 * Open details of park to owner
 * @author Sergiy Tolok
 */
public class ShowParkToOwnerCommand extends Command {

    public static final String PARK_SELECT = "park_select";

    public String perform() {
        Owner owner = (Owner) req.getSession().getAttribute(OWNER);
        //Get Park which should open
        Park park = ((ArrayList<Park>) owner.getParkCollection()).get(Integer.parseInt(req.getParameter(PARK_SELECT)));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
        //Get addition infoprmation about park
        daoFactory.getParkDAO().extendPark(park);
        park.setPlantInfoCollection((ArrayList<PlantInfo>) req.getSession().getAttribute(PLANT_INFO_COLLECTION));
        park.setOwner(owner);
        //Set in which plants is already exist order
        owner.setOrderToPlants(park);
        req.getSession().setAttribute(MESSAGE, null);
        req.getSession().setAttribute(PARK, park);
        req.getSession().setAttribute(ORDERS, owner.getOrdersInPark(park));
        return PARK_TO_OWNER;
    }
}
