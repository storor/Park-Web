/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.tolok.projects.project4.model.dao;

import java.util.ArrayList;
import ua.tolok.projects.project4.model.entities.Service;

/**
 * Oparating whith Service in data storage
 * @author Sergiy Tolok
 */
public interface ServiceDAO {
    /**
     * Save to data storage new instance of Service
     * @param service new instance of Service
     * @return serviceId
     */
    public int insertService(Service service);
    /**
     * Get list of all Services in system
     * @return 
     */
    public ArrayList<Service> getAllServices();
}
