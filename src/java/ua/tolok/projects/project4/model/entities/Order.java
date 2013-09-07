package ua.tolok.projects.project4.model.entities;

import java.io.Serializable;
import java.util.Date;


public class Order implements Serializable {
    
    private Integer orderId;
    private Date executionDate;
    private Date dateAdded;
    private Date confirmDate;
    private Forester forester;
    private Service service;
    private Plant plant;
    private Owner owner;

    public Order() {
    }

    public Order(Integer orderId) {
        this.orderId = orderId;
    }

    public Order(Integer orderId, Date dateAdded) {
        this.orderId = orderId;
        this.dateAdded = dateAdded;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }
    
    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Forester getForester() {
        return forester;
    }

    public void setForester(Forester foresterId) {
        this.forester = foresterId;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service serviceId) {
        this.service = serviceId;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plantId) {
        this.plant = plantId;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner ownerId) {
        this.owner = ownerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.tolok.projects.project4.model.entities.Order1[ orderId=" + orderId + " ]";
    }
    
}
