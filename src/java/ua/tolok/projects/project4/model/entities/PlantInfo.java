package ua.tolok.projects.project4.model.entities;

import java.io.Serializable;

/**
 * PlantInfo entity
 * @author Sergiy Tolok
 */
public class PlantInfo implements Serializable {
    private Integer plantInfoId;
    private String type;
    private String sort;
    private String typeRu;
    private String sortRu;

    public PlantInfo() {
    }

    public PlantInfo(Integer plantInfoId) {
        this.plantInfoId = plantInfoId;
    }

    public Integer getPlantInfoId() {
        return plantInfoId;
    }

    public void setPlantInfoId(Integer plantInfoId) {
        this.plantInfoId = plantInfoId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getTypeRu() {
        return typeRu;
    }

    public void setTypeRu(String typeRu) {
        this.typeRu = typeRu;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
    
    public String getSortRu() {
        return sortRu;
    }

    public void setSortRu(String sortRu) {
        this.sortRu = sortRu;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plantInfoId != null ? plantInfoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlantInfo)) {
            return false;
        }
        PlantInfo other = (PlantInfo) object;
        if ((this.plantInfoId == null && other.plantInfoId != null) || (this.plantInfoId != null && !this.plantInfoId.equals(other.plantInfoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.tolok.projects.project4.model.entities.PlantInfo[ plantInfoId=" + plantInfoId + " ]";
    }
    
}
