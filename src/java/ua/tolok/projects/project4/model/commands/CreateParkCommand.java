package ua.tolok.projects.project4.model.commands;

import java.io.UnsupportedEncodingException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.tolok.projects.project4.model.dao.DAOFactory;
import ua.tolok.projects.project4.model.dao.ParkDAO;
import ua.tolok.projects.project4.model.entities.Forester;
import ua.tolok.projects.project4.model.entities.Owner;
import ua.tolok.projects.project4.model.entities.Park;

/**
 * Create new park by owner
 * @author Sergiy Tolok
 */
public class CreateParkCommand extends Command {

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String FORSTER_SELECT = "forster_select";
    private DAOFactory daoFactory;

    public String perform() {
        daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
        ParkDAO parkDAO = daoFactory.getParkDAO();
        //Create new instance of Park
        Park park = new Park();
        try {
            park.setName(new String(req.getParameter(NAME).getBytes("ISO-8859-1"), "UTF-8"));
            park.setDescription(new String(req.getParameter(DESCRIPTION).getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CreateParkCommand.class.getName()).log(Level.ERROR, null, ex);
        }
        Owner owner = (Owner) req.getSession().getAttribute(OWNER);
        park.setOwner(owner);
        park.setForester(new Forester(Integer.parseInt(req.getParameter(FORSTER_SELECT))));
        //Save to data storge
        int insertPark = parkDAO.insertPark(park);
        if (insertPark > 0) {
            park.setParkId(insertPark);
            //Add to Owner park Collection
            owner.getParkCollection().add(park);
            req.getSession().setAttribute(OWNER, owner);
        }
        return OWNER_MAIN;
    }
}
