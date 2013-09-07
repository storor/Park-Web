package ua.tolok.projects.project4.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Park entity
 * @author Sergiy Tolok
 */
public class Park implements Serializable {

    private Integer parkId;
    private String name;
    private String description;
    private Collection<Plant> plantCollection;
    private Forester forester;
    private Owner owner;

    public Park() {
    }


    public Park(Integer parkId) {
        this.parkId = parkId;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
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

    public Collection<Plant> getPlantCollection() {
        return plantCollection;
    }

    public void setPlantCollection(Collection<Plant> plantCollection) {
        this.plantCollection = plantCollection;
    }

    public Forester getForester() {
        return forester;
    }

    public void setForester(Forester forester) {
        this.forester = forester;
    }

    public Plant getPlant(int plantId) {
        if (plantCollection != null) {
            for (Plant plant : plantCollection) {
                if (plant.getPlantId()==plantId) {
                    return plant;
                }
            }
        }
        return null;
    }
    
    /**
     * Set PlantInfo to every plant in the park
     * @param plantInfoCollection 
     */
    public void setPlantInfoCollection(ArrayList<PlantInfo> plantInfoCollection) {
        if (this.plantCollection != null && plantInfoCollection != null) {
            for (Plant plant : this.plantCollection) {
                for (PlantInfo plantInfo : plantInfoCollection) {
                    if (plantInfo.getPlantInfoId() == plant.getPlantInfo().getPlantInfoId()) {
                        plant.setPlantInfo(plantInfo);
                        break;
                    }
                }
            }
        }

    }
    
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parkId != null ? parkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Park)) {
            return false;
        }
        Park other = (Park) object;
        if ((this.parkId == null && other.parkId != null) || (this.parkId != null && !this.parkId.equals(other.parkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.tolok.projects.project4.model.entities.Park[ parkId=" + parkId + " ]";
    }
}
