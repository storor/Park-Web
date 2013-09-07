package ua.tolok.projects.project4.model.commands;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.tolok.projects.project4.model.dao.DAOFactory;
import ua.tolok.projects.project4.model.dao.ServiceDAO;
import ua.tolok.projects.project4.model.entities.Service;

/**
 * Create new service command
 * @author Sergiy Tolok
 */
public class CreateServiceCommand extends Command {

    public static final String NAME = "name";
    public static final String NAME_RUS = "name_rus";
    public static final String DESCRIPTION = "description";
    public static final String DESCRIPTION_RUS = "description_rus";
    private DAOFactory daoFactory;

    public String perform() {

        //Create new instance of Service
        Service service = new Service();
        try {
            service.setName((new String(req.getParameter(NAME).getBytes("ISO-8859-1"), "UTF-8")).trim());
            service.setNameRus((new String(req.getParameter(NAME_RUS).getBytes("ISO-8859-1"), "UTF-8")).trim());
            service.setDescription((new String(req.getParameter(DESCRIPTION).getBytes("ISO-8859-1"), "UTF-8")).trim());
            service.setDescriptionRus((new String(req.getParameter(DESCRIPTION_RUS).getBytes("ISO-8859-1"), "UTF-8")).trim());
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CreateServiceCommand.class.getName()).log(Level.ERROR, null, ex);
        }

        daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
        ServiceDAO serviceDAO = daoFactory.getServiceDAO();
        
        //Save to data storage
        int insertService = serviceDAO.insertService(service);

        if (insertService > 0) {
            service.setServiceId(insertService);

            ArrayList<Service> services = (ArrayList<Service>) req.getSession().getAttribute(SERVICES);
            if (services == null) {
                services = new ArrayList<Service>();
            }
            //Add to current list of services
            services.add(service);
            req.getSession().setAttribute(SERVICES, services);
        }
        return ADMIN_PAGE;
    }
}
