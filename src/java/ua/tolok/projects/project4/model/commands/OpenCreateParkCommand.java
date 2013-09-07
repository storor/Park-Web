package ua.tolok.projects.project4.model.commands;

import ua.tolok.projects.project4.model.dao.DAOFactory;
import ua.tolok.projects.project4.model.dao.ForesterDAO;

/**
 * Open page for creating new park
 * @author Sergiy Tolok
 */
public class OpenCreateParkCommand extends Command{
    private DAOFactory daoFactory;

    public String perform() {
        //Load from data storage all avaible foresters
        daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
        ForesterDAO foresterDAO = daoFactory.getForesterDAO();
        req.getSession().setAttribute("foresters", foresterDAO.getForesters());
        return CREATE_PARK;
    }
    
}
