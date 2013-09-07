package ua.tolok.projects.project4.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Forester entity
 * @author Sergiy Tolok
 */
public class Forester implements Serializable {

    private Integer foresterId;
    private Integer qualification;
    private Collection<Order> orderCollection;
    private Collection<Park> parkCollection;
    private UserInfo userInfo;

    public Forester() {
    }

    public Forester(Integer foresterId) {
        this.foresterId = foresterId;
    }

    public Integer getForesterId() {
        return foresterId;
    }

    public void setForesterId(Integer foresterId) {
        this.foresterId = foresterId;
    }

    public Integer getQualification() {
        return qualification;
    }

    public void setQualification(Integer qualification) {
        this.qualification = qualification;
    }

    public Collection<Order> getOrderCollection() {
        if (orderCollection == null) {
            orderCollection=new ArrayList<Order>();
        }
        return orderCollection;
    }

    
    public void setOrderCollection(Collection<Order> order1Collection) {
        this.orderCollection = order1Collection;
    }

    /**
     * Get park from parkCollection by parkId
     * @param parkId
     * @return 
     */
    public Park getPark(int parkId) {
        if (parkCollection != null) {
            for (Park park : parkCollection) {
                if (park.getParkId() == parkId) {
                    return park;
                }
            }
        }
        return null;
    }
    
    /**
     * Set details to orders
     * @param services
     * @param plantInfoCollection 
     */
    public void setDetails(ArrayList<Service> services, ArrayList<PlantInfo> plantInfoCollection) {
        for (Order order : this.getOrderCollection()) {
            for (Service service : services) {
                if (service.getServiceId() == order.getService().getServiceId()) {
                    order.setService(service);
                    break;
                }
            }
            for (PlantInfo plantInfo : plantInfoCollection) {
                if (plantInfo.getPlantInfoId() == order.getPlant().getPlantInfo().getPlantInfoId()) {
                    order.getPlant().setPlantInfo(plantInfo);
                    break;
                }
            }
        }

    }
    
    public Collection<Park> getParkCollection() {
        if (parkCollection == null) {
            parkCollection=new ArrayList<Park>();
        }
        return parkCollection;
    }

    public void setParkCollection(Collection<Park> parkCollection) {
        this.parkCollection = parkCollection;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfoId) {
        this.userInfo = userInfoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foresterId != null ? foresterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Forester)) {
            return false;
        }
        Forester other = (Forester) object;
        if ((this.foresterId == null && other.foresterId != null) || (this.foresterId != null && !this.foresterId.equals(other.foresterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = null;
        if (userInfo != null) {
            sb = new StringBuilder();
            sb.append(this.getUserInfo().getLogin());
        }
        return sb.toString();
    }
}
