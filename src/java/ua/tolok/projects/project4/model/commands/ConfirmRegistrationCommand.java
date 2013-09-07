package ua.tolok.projects.project4.model.commands;

import java.io.UnsupportedEncodingException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.tolok.projects.project4.model.dao.DAOFactory;
import ua.tolok.projects.project4.model.dao.ForesterDAO;
import ua.tolok.projects.project4.model.dao.OwnerDAO;
import ua.tolok.projects.project4.model.dao.UserInfoDAO;
import ua.tolok.projects.project4.model.entities.Forester;
import ua.tolok.projects.project4.model.entities.Owner;
import ua.tolok.projects.project4.model.entities.UserInfo;

/**
 * Save date which new user iputed
 * @author Sergiy Tolok
 */
public class ConfirmRegistrationCommand extends Command {

    DAOFactory daoFactory;
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String CONFIRN_PASSWORD = "conf_password";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String MIDLENAME = "midlename";
    public static final String ROLE = "role";

    public String perform() {
        daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
        UserInfoDAO userInfoDAO = daoFactory.getUserInfoDAO();
        UserInfo userInfo = null;
        try {
            //Get login field
            String login = new String(req.getParameter(LOGIN).getBytes("ISO-8859-1"), "UTF-8");
            //Test if that are epty
            if (login.equals("")) {
                req.getSession().setAttribute(MESSAGE, "input_login");
                return REGISTRATION;
            }
            String password = new String(req.getParameter(PASSWORD).getBytes("ISO-8859-1"), "UTF-8");
            String confirn_password = new String(req.getParameter(CONFIRN_PASSWORD).getBytes("ISO-8859-1"), "UTF-8");
            //Check if current login is already using
            boolean userExist = userInfoDAO.isLoginExist(login);

            if (userExist) {
                req.getSession().setAttribute(MESSAGE, "user_exist");
                return REGISTRATION;
            } else if (!password.equals(confirn_password) || password.equals("")) {
                req.getSession().setAttribute(MESSAGE, "incorect_password");
                return REGISTRATION;
            }
            //If all Ok create new user
            userInfo = new UserInfo();

            userInfo.setRole(req.getParameter(ROLE).equals(OWNER));
            userInfo.setLogin(login);
            userInfo.setPassword(password);
            userInfo.setFirstName(new String(req.getParameter(FIRSTNAME).getBytes("ISO-8859-1"), "UTF-8"));
            userInfo.setSurname(new String(req.getParameter(LASTNAME).getBytes("ISO-8859-1"), "UTF-8"));
            userInfo.setMidleName(new String(req.getParameter(MIDLENAME).getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ConfirmRegistrationCommand.class.getName()).log(Level.ERROR, null, ex);
        }
        //Save in date storage
        int userInfoId = userInfoDAO.insertUserInfo(userInfo);
        if (userInfoId > 0) {
            CommandUtility.loadServices(daoFactory,req);
            CommandUtility.loadPlantInfoCollection(daoFactory, req);
            userInfo.setUserInfoId(userInfoId);
            //Choose which user type and open main page
            if (userInfo.isOwner()) {
                Owner owner = createOwner(userInfo);
                req.getSession().setAttribute(OWNER, owner);
                return OWNER_MAIN;


            } else {
                Forester forester = createForester(userInfo);
                req.getSession().setAttribute(FORESTER, forester);
                return FORESTER_MAIN;
            }
        }
        return INDEX_PAGE;

    }

    /**
     * Create new Forester instance by UserInfo object and save to data storage
     * @param userInfo
     * @return new Forester
     */
    Forester createForester(UserInfo userInfo) {
        ForesterDAO foresterDAO = daoFactory.getForesterDAO();
        Forester forester = new Forester();
        forester.setUserInfo(userInfo);
        //Save to data storage
        forester.setForesterId(foresterDAO.insertForester(forester));
        return forester;
    }

    /**
     * Create new Owner instance by UserInfo object and save to data storage
     * @param userInfo
     * @return 
     */
    Owner createOwner(UserInfo userInfo) {
        OwnerDAO ownerDAO = daoFactory.getOwnerDAO();
        Owner owner = new Owner();
        owner.setUserInfo(userInfo);
        //Save to data storage
        owner.setOwnerId(ownerDAO.insertOwner(owner));
        return owner;
    }
}
