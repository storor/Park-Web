package ua.epam.projects.project4.model.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class describe main featers of commands
 *
 * @author Sergiy Tolok
 */
public abstract class Command {

    //JSP page's url
    public static final String INDEX_PAGE = "/Project4/index.jsp";
    public static final String ERROR_PAGE = "/Project4/error_page.jsp";
    public static final String ADMIN_PAGE = "/Project4/admin.jsp";
    public static final String OWNER_MAIN = "/Project4/owner_main.jsp";
    public static final String CREATE_PARK = "/Project4/create_park.jsp";
    public static final String CREATE_SERVICE = "/Project4/create_service.jsp";
    public static final String FORESTER_MAIN = "/Project4/forester_main.jsp";
    public static final String PARK_TO_FORESTER = "/Project4/park_to_forester.jsp";
    public static final String PARK_TO_OWNER = "/Project4/park_to_owner.jsp";
    public static final String REGISTRATION = "/Project4/registration.jsp";
    public static final String CREATE_PLANT = "/Project4/create_plant.jsp";
    
    //Attrubute's names
    public static final String OWNER = "owner";
    public static final String FORESTER = "forester";
    public static final String SERVICES = "services";
    public static final String PARK = "park";
    public static final String ORDERS = "orders";
    public static final String PLANT_INFO_COLLECTION = "plantInfoCollection";
    public static final String MESSAGE = "message";
    
    HttpServletRequest req;
    HttpServletResponse res;

    public void setReqRes(HttpServletRequest req, HttpServletResponse res) {
        this.req = req;
        this.res = res;
    }

    /**
     * Action which should do Command class
     *
     * @return URL of page which will open after command executed
     */
    public abstract String perform();
}
