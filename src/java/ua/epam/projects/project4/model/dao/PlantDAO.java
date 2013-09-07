package ua.epam.projects.project4.model.dao;

import ua.epam.projects.project4.model.entities.Plant;

/**
 * Oparating whith Plant in data storage
 * @author Sergiy Tolok
 */
public interface PlantDAO {
    /**
     * Save to data storage new instance of Plant
     * @param plant new instance of Plant
     * @return plantId
     */
    public int insertPlant(Plant plant); 
    /**
     * Update information about plant
     * @param plant
     * @return 
     */
    public boolean updatePlant(Plant plant); 
}
