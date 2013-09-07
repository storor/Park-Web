package ua.epam.projects.project4.model.dao;

import java.util.ArrayList;
import ua.epam.projects.project4.model.entities.Owner;
import ua.epam.projects.project4.model.entities.Park;

/**
 * Describe which actions could with Park entity
 * @author Sergiy Tolok
 */
public interface ParkDAO {
    /**
     * Save to data storage new instance of Park
     * @param park new instance of Park
     * @return 
     */
    public int insertPark(Park park);
   /**
    * Load addition information about park
    * @param park 
    */
    public void extendPark(Park park); 
    /**
     * Load list of park which belong to owner
     * @param owner
     * @return list of park which belong to owner
     */
    public ArrayList<Park> findParks(Owner owner);
}
