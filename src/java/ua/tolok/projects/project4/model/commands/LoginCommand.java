package ua.tolok.projects.project4.model.commands;

import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;
import ua.tolok.projects.project4.model.dao.DAOFactory;
import ua.tolok.projects.project4.model.dao.ForesterDAO;
import ua.tolok.projects.project4.model.dao.OwnerDAO;
import ua.tolok.projects.project4.model.dao.UserInfoDAO;
import ua.tolok.projects.project4.model.entities.Forester;
import ua.tolok.projects.project4.model.entities.Owner;
import ua.tolok.projects.project4.model.entities.PlantInfo;
import ua.tolok.projects.project4.model.entities.Service;
import ua.tolok.projects.project4.model.entities.UserInfo;

/**
 * Entry point of application for user where he types his credentials
 * Or redirecting on registration page
 * @author Sergiy Tolok
 */
public class LoginCommand extends Command {

    public static final String PASSWORD = "password";
    public static final String LOGIN = "login";

    public String perform() {

       
        
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
        UserInfoDAO userInfoDAO = daoFactory.getUserInfoDAO();

        //Get credentials
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);

        //Check if user exist
        UserInfo userInfo = userInfoDAO.findUserInfo(login, password);
        if (userInfo != null) {
            //Get general information about system entyties
            CommandUtility.loadServices(daoFactory,req);
            CommandUtility.loadPlantInfoCollection(daoFactory, req);
            //Loggin user login and date of entry 
            Logger.getLogger(LoginCommand.class.getName()).info(loginMessage(login));
            //Find out which type of user is he
            if(userInfo.isOwner()==null){
                req.getSession().setAttribute("admin", userInfo);
                return ADMIN_PAGE;
            }
            if (userInfo.isOwner()) {
                OwnerDAO ownerDAO = daoFactory.getOwnerDAO();
                Owner owner = ownerDAO.findOwner(userInfo);
                if (owner != null) {
                    //Extends information about Owner Orders, Parks, etc
                    owner.setDetails(
                            (ArrayList<Service>)req.getSession().getAttribute(SERVICES),
                            (ArrayList<PlantInfo>)req.getSession().getAttribute(PLANT_INFO_COLLECTION));
                    req.getSession().setAttribute(OWNER, owner);
                    return OWNER_MAIN;
                }

            } else {
                ForesterDAO foresterDAO = daoFactory.getForesterDAO();
                Forester forester = foresterDAO.findForester(userInfo);
                if (forester != null) {
                    //Extends information about Forester Orders, Parks, etc
                    forester.setDetails(
                            (ArrayList<Service>)req.getSession().getAttribute(SERVICES),
                            (ArrayList<PlantInfo>)req.getSession().getAttribute(PLANT_INFO_COLLECTION));
                    req.getSession().setAttribute(FORESTER, forester);
                    return FORESTER_MAIN;
                }
            }
        }
        req.getSession().setAttribute(MESSAGE, "incorect_credentials");
        return INDEX_PAGE;
    }
    
    /**
     * Create INFO message for Logger
     * @param login user login
     * @return INFO message
     */
    String loginMessage(String login){
        StringBuilder sb = new StringBuilder();
        sb.append(login);
        sb.append(" logged at ");
        sb.append(new Date().toString());
        return sb.toString();
    }
}
