package ua.tolok.projects.project4.model.commands;

import java.util.ArrayList;
import ua.tolok.projects.project4.model.dao.DAOFactory;
import ua.tolok.projects.project4.model.entities.Forester;
import ua.tolok.projects.project4.model.entities.Park;
import ua.tolok.projects.project4.model.entities.PlantInfo;

/**
 * Open park details to forester
 * @author Sergiy Tolok
 */
public class ShowParkToForesterCommand extends Command{

    public String perform() {
        Forester forester = (Forester)req.getSession().getAttribute(FORESTER);
        //Get index of park which user selected
        int parkSelested= Integer.parseInt(req.getParameter("park_select"));
        
        Park park = ((ArrayList<Park>)forester.getParkCollection()).get(parkSelested);
        //Load details about selected park
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
        daoFactory.getParkDAO().extendPark(park);
        park.setPlantInfoCollection((ArrayList<PlantInfo>)req.getSession().getAttribute(PLANT_INFO_COLLECTION));
        park.setForester(forester);
        
        req.getSession().setAttribute(PARK, park);
        return PARK_TO_FORESTER;
    }
    
}
