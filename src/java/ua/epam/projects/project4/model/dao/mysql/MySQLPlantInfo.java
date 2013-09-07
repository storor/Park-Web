package ua.epam.projects.project4.model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.epam.projects.project4.model.dao.PlantInfoDAO;
import ua.epam.projects.project4.model.entities.PlantInfo;

/**
 * Implementation of PlantInfoDAO for MySQL data base
 * @author Sergiy Tolok
 */
public class MySQLPlantInfo implements PlantInfoDAO {

    private Connection connection;
    //Name of table
    public static final String PLANT_INFO = "plant_info";
    /**
     * Names of columns in forester table in data base
     */
    public static final String PLANT_INFO_ID = "plant_info.plant_info_id";
    public static final String TYPE = "plant_info.type";
    public static final String TYPE_RU = "plant_info.type_ru";
    public static final String SORT = "plant_info.sort";
    public static final String SORT_RU = "plant_info.sort_ru";

    public int insertPlantInfo(PlantInfo plantInfo) {
        int insertedKey = 0;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Create query string
            String query = MySQLUtility.createInsertStatment(PLANT_INFO, TYPE, TYPE_RU, SORT, SORT_RU);
            PreparedStatement statment = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statment.setString(1, plantInfo.getType());
            statment.setString(2, plantInfo.getTypeRu());
            statment.setString(3, plantInfo.getSort());
            statment.setString(4, plantInfo.getSortRu());

            int result = statment.executeUpdate();
            if (result > 0) {
                //If query executed successfully - get plantInfoId
                insertedKey = MySQLUtility.getKey(statment);
            }
            Logger.getLogger(MySQLPlantInfo.class.getName()).info((new Date()).toString() + " " + query);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPlantInfo.class.getName()).log(Level.ERROR, null, ex);
        }
        return insertedKey;
    }

    public ArrayList<PlantInfo> getAllPlantInfo() {
        ArrayList<PlantInfo> plantInfoCollection = null;
        try {
            connection = MySqlDAOFactory.getConnection();
            //Set in which tables gets data
            String[] tables = new String[]{PLANT_INFO};
            //Create query string - getting all columns
            String query = MySQLUtility.createSelectStatment(tables, null);
            PreparedStatement statment = connection.prepareStatement(query);
            ResultSet result = statment.executeQuery();
            //Get result plantInfoCollection
            while (result.next()) {
                if (plantInfoCollection == null) {
                    plantInfoCollection = new ArrayList<PlantInfo>();
                }
                PlantInfo plantInfo = new PlantInfo();
                plantInfo.setPlantInfoId(result.getInt(PLANT_INFO_ID));
                plantInfo.setType(result.getString(TYPE));
                plantInfo.setTypeRu(result.getString(TYPE_RU));
                plantInfo.setSort(result.getString(SORT));
                plantInfo.setSortRu(result.getString(SORT_RU));
                plantInfoCollection.add(plantInfo);
            }
            connection.close();
            Logger.getLogger(MySQLPlantInfo.class.getName()).info((new Date()).toString() + " " + query);

        } catch (SQLException ex) {
            Logger.getLogger(MySQLPlantInfo.class
                    .getName()).log(Level.ERROR, null, ex);
        }
        return plantInfoCollection;
    }
}
