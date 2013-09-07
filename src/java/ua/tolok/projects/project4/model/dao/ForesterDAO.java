package ua.tolok.projects.project4.model.dao;

import java.util.ArrayList;
import ua.tolok.projects.project4.model.entities.Forester;
import ua.tolok.projects.project4.model.entities.UserInfo;

/**
 * Oparating whith Forester in data storage
 * @author Sergiy Tolok
 */
public interface ForesterDAO {
    /**
     * Save to data storage new instance of Forester
     * @param forester new instance of Forester
     * @return foresterId
     */
     public int insertForester(Forester forester); 
     /**
      * Load Forester from data storage
      * @param userInfo UserInfo object which is belong to Forester
      * @return Forester object
      */
     public Forester findForester(UserInfo userInfo);
     /**
      * Get list of all avaible foresters
      * @return 
      */
     public ArrayList<Forester> getForesters(); 
     /**
      * Get UserInfo for Forester
      * @param forester 
      */
     public void extendUserInfo(Forester forester);
}
