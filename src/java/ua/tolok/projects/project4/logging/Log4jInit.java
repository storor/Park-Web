/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.tolok.projects.project4.logging;

import java.io.File;
import javax.servlet.*;
import org.apache.log4j.PropertyConfigurator;
/**
 * Class provides initialize of Log4j Logger
 * @author Sergiy Tolok
 */
public class Log4jInit implements ServletContextListener{

    public void contextInitialized(ServletContextEvent event) {
        String homeDir=event.getServletContext().getRealPath("/");
        //Set direction to direction whith property file for Log4j Logger 
        File propertiesFile=new File(homeDir,"WEB-INF/log4j.properties");
        PropertyConfigurator.configure(propertiesFile.toString());
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
    
}
