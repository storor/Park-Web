package ua.epam.projects.project4.view;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.epam.projects.project4.model.entities.PlantInfo;
import ua.epam.projects.project4.model.entities.Service;

/**
 * JSP tag which displays value in depending on Locale
 * @author Sergiy Tolok
 */
public class GetLocaleValueTag extends TagSupport {

    private String locale = "";
    private Service serviceName;
    private Service serviceDescription;
    private PlantInfo plantType;
    private PlantInfo plantSort;

    @Override
    public int doStartTag() {
        JspWriter out = pageContext.getOut();
        if(locale.equals("")){
            //Get locale which user sets 
            Locale loc = Locale.getDefault();
            locale = loc.toString();
        }
        try {
            //Depending on which attribute is seted print values
            if (serviceName != null) {
                if (locale.equals("ru_RU")) {
                    out.print(serviceName.getNameRus());
                } else {
                    out.print(serviceName.getName());
                }
            }if (serviceDescription != null) {
                if (locale.equals("ru_RU")) {
                    out.print(serviceDescription.getDescriptionRus());
                } else {
                    out.print(serviceDescription.getDescription());
                }
            }if (plantType != null) {
                if (locale.equals("ru_RU")) {
                    out.print(plantType.getTypeRu());
                } else {
                    out.print(plantType.getType());
                }
            }if (plantSort != null) {
                if (locale.equals("ru_RU")) {
                    out.print(plantSort.getSortRu());
                } else {
                    out.print(plantSort.getSort());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GetLocaleValueTag.class.getName()).log(Level.ERROR, null, ex);
        }
        return SKIP_BODY;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setServiceName(Service service) {
        this.serviceName = service;
    }

    public void setServiceDescription(Service service) {
        this.serviceDescription = service;
    }

    public void setPlantType(PlantInfo plantInfo) {
        this.plantType = plantInfo;
    }

    public void setPlantSort(PlantInfo plantInfo) {
        this.plantSort = plantInfo;
    }
}
