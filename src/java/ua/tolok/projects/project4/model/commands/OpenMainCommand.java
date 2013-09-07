package ua.tolok.projects.project4.model.commands;

import java.util.ArrayList;
import ua.tolok.projects.project4.model.dao.DAOFactory;
import ua.tolok.projects.project4.model.dao.ForesterDAO;
import ua.tolok.projects.project4.model.dao.OwnerDAO;
import ua.tolok.projects.project4.model.entities.Forester;
import ua.tolok.projects.project4.model.entities.Owner;
import ua.tolok.projects.project4.model.entities.PlantInfo;
import ua.tolok.projects.project4.model.entities.Service;

/**
 * Open main page for user wehter he owner or forester or admin
 * @author Sergiy Tolok
 */
public class OpenMainCommand extends Command {

    private DAOFactory daoFactory;

    public String perform() {
        daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
        CommandUtility.loadServices(daoFactory, req);
        CommandUtility.loadPlantInfoCollection(daoFactory, req);
        //Get from Session Owner attribute
        Owner owner = (Owner) req.getSession().getAttribute(OWNER);
        if (owner != null) {
            OwnerDAO ownerDAO = daoFactory.getOwnerDAO();
            owner = ownerDAO.findOwner(owner.getUserInfo());
            owner.setDetails((ArrayList<Service>) req.getSession().getAttribute(SERVICES),
                    (ArrayList<PlantInfo>) req.getSession().getAttribute(PLANT_INFO_COLLECTION));
            req.getSession().setAttribute(OWNER, owner);
            return OWNER_MAIN;
        }
        //Get from Session Forester attribute
        Forester forester = (Forester) req.getSession().getAttribute(FORESTER);
        if (forester != null) {
            ForesterDAO foresterDAO = daoFactory.getForesterDAO();
            forester = foresterDAO.findForester(forester.getUserInfo());
            forester.setDetails((ArrayList<Service>) req.getSession().getAttribute(SERVICES),
                    (ArrayList<PlantInfo>) req.getSession().getAttribute(PLANT_INFO_COLLECTION));
            req.getSession().setAttribute(FORESTER, forester);
            return FORESTER_MAIN;
        }
        //Get from Session admin attribute
        if (req.getSession().getAttribute("admin") != null) {
            return ADMIN_PAGE;
        } else {
            //If neither of them is exist in Session - return to login page
            return INDEX_PAGE;
        }

    }
}
