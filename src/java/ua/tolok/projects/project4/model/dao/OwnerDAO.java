package ua.tolok.projects.project4.model.dao;

import ua.tolok.projects.project4.model.entities.Owner;
import ua.tolok.projects.project4.model.entities.UserInfo;

/**
 * Oparating whith Owner in data storage
 * @author Sergiy Tolok
 */
public interface OwnerDAO {
    /**
     * Save to data storage new instance of Owner
     * @param owner
     * @return 
     */
    public int insertOwner(Owner owner);
    /**
      * Get UserInfo for Owner
      * @param owner 
      */
     public void extendUserInfo(Owner owner);
     /**
      * Load Owner from data storage
      * @param userInfo UserInfo object which is belong to Owner
      * @return Owner object
      */
     public Owner findOwner(UserInfo userInfo);  
}
