package ua.epam.projects.project4.model.commands;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.epam.projects.project4.model.dao.DAOFactory;
import ua.epam.projects.project4.model.dao.PlantInfoDAO;
import ua.epam.projects.project4.model.entities.PlantInfo;

/**
 * Create new plant type and sort
 * @author Sergiy Tolok
 */
public class CreatePlantInfoCommand extends Command {

    public static final String TYPE = "type";
    public static final String SORT = "sort";
    public static final String TYPE_RU = "typeRu";
    public static final String SORT_RU = "sortRu";

    public String perform() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
        //Get list of existed types of plant
        ArrayList<PlantInfo> plantInfoCollection = (ArrayList<PlantInfo>) 
                req.getSession().getAttribute(PLANT_INFO_COLLECTION);

        //Create new instance 
        PlantInfo plantInfo = new PlantInfo();
        try {
            plantInfo.setSort(new String(req.getParameter(SORT).getBytes("ISO-8859-1"), "UTF-8"));
            plantInfo.setType(new String(req.getParameter(TYPE).getBytes("ISO-8859-1"), "UTF-8"));
            plantInfo.setSortRu(new String(req.getParameter(SORT_RU).getBytes("ISO-8859-1"), "UTF-8"));
            plantInfo.setTypeRu(new String(req.getParameter(TYPE_RU).getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CreatePlantInfoCommand.class.getName()).log(Level.ERROR, null, ex);
        }
        PlantInfoDAO plantInfoDAO = daoFactory.getPlantInfoDAO();
        //Save to data storage
        int insertPlantInfo = plantInfoDAO.insertPlantInfo(plantInfo);
        if (insertPlantInfo > 0) {
            plantInfo.setPlantInfoId(insertPlantInfo);
            plantInfoCollection.add(plantInfo);
            req.getSession().setAttribute(PLANT_INFO_COLLECTION, plantInfoCollection);

        }
        return CREATE_PLANT;
    }
}
