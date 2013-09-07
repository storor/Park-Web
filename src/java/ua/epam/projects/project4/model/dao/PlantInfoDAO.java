/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.projects.project4.model.dao;

import java.util.ArrayList;
import ua.epam.projects.project4.model.entities.PlantInfo;

/**
 * Oparating whith PlantInfo in data storage
 * @author Sergiy Tolok
 */
public interface PlantInfoDAO {

    /**
     * Save to data storage new instance of PlantInfo
     * @param plantInfo new instance of PlantInfo
     * @return PlantInfoId
     */
    public int insertPlantInfo(PlantInfo plantInfo);
    /**
     * Get list of all PlantInfo in system
     * @return list of all PlantInfo in system
     */
    public ArrayList<PlantInfo> getAllPlantInfo();
}
