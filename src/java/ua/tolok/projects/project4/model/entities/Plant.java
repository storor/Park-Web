package ua.tolok.projects.project4.model.entities;

import java.io.Serializable;
import java.util.Date;
/**
 * Plant entity
 * @author Sergiy Tolok
 */
public class Plant implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer plantId;
    private Date plantedDate;
    private Date cutedDate;
    private Park park;
    private PlantInfo plantInfo;
    private Order order;

    public Plant() {
    }

    public Plant(Integer plantId) {
        this.plantId = plantId;
    }

    public Plant(Integer plantId, Date plantedDate) {
        this.plantId = plantId;
        this.plantedDate = plantedDate;
    }

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public Date getPlantedDate() {
        return plantedDate;
    }

    public void setPlantedDate(Date plantedDate) {
        this.plantedDate = plantedDate;
    }
    
    public Date getCutedDate() {
        return cutedDate;
    }

    public void setCutedDate(Date cutedDate) {
        this.cutedDate = cutedDate;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park parkId) {
        this.park = parkId;
    }

    public PlantInfo getPlantInfo() {
        return plantInfo;
    }

    public void setPlantInfo(PlantInfo plantInfo) {
        this.plantInfo = plantInfo;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plantId != null ? plantId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plant)) {
            return false;
        }
        Plant other = (Plant) object;
        if ((this.plantId == null && other.plantId != null) || (this.plantId != null && !this.plantId.equals(other.plantId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.tolok.projects.project4.model.entities.Plant[ plantId=" + plantId + " ]";
    }
    
}
