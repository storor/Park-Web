package ua.tolok.projects.project4.model.entities;

import java.io.Serializable;
import java.util.Collection;

/**
 * Service entity
 *
 * @author Sergiy Tolok
 */
public class Service implements Serializable {

    /**
     * Name of service type Plant
     */
    public static final String PLANT = "plant";
    /**
     * Name of service type Cut down
     */
    public static final String CUT_DOWN = "cut down";

    private Integer serviceId;
    private String name;
    private String description;
    private String nameRus;
    private String descriptionRus;
    private Collection<Order> orderCollection;

    public Service() {
    }

    public Service(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameRus() {
        return nameRus;
    }

    public void setNameRus(String nameRus) {
        this.nameRus = nameRus;
    }

    public String getDescriptionRus() {
        return descriptionRus;
    }

    public void setDescriptionRus(String descriptionRus) {
        this.descriptionRus = descriptionRus;
    }

    public Collection<Order> getOrderCollection() {
        return orderCollection;
    }

    public void setOrderCollection(Collection<Order> orderCollection) {
        this.orderCollection = orderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceId != null ? serviceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.serviceId == null && other.serviceId != null) || (this.serviceId != null && !this.serviceId.equals(other.serviceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
