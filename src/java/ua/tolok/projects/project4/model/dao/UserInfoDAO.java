/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.tolok.projects.project4.model.dao;

import ua.tolok.projects.project4.model.entities.UserInfo;

/**
 * Oparating whith UserInfo in data storage
 * @author Sergiy Tolok
 */
public interface UserInfoDAO {
    /**
     * Save to data storage new instance of UserInfo
     * @param userInfo new instance of UserInfo
     * @return userInfoId
     */
     public int insertUserInfo(UserInfo userInfo); 
     /**
      * Get UserInfo object by login and password
      * @param login user login
      * @param password user password
      * @return UserInfo object
      */
     public UserInfo findUserInfo(String login, String password);
     /**
      * Check is login is already in use
      * @param login
      * @return 
      */
     public boolean isLoginExist(String login);
}
