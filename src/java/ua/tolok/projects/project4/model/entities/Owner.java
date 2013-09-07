package ua.tolok.projects.project4.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Owner entity
 * @author Sergiy Tolok
 */
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer ownerId;
    private String organisation;
    private Collection<Order> orderCollection;
    private Collection<Park> parkCollection;
    private UserInfo userInfo;

    public Owner() {
    }

    public Owner(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getOrganisation() {
        return organisation;
    }

    /**
     * Set details to orders
     * @param services
     * @param plantInfoCollection 
     */
    public void setDetails(ArrayList<Service> services, ArrayList<PlantInfo> plantInfoCollection) {
        if (this.orderCollection != null) {
            for (Order order : this.orderCollection) {
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
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public Collection<Order> getOrderCollection() {
        if (orderCollection == null) {
            orderCollection = new ArrayList<Order>();
        }
        return orderCollection;
    }

    public void setOrderCollection(Collection<Order> order1Collection) {
        this.orderCollection = order1Collection;
    }

    /**
     * Set orders to plants in the park
     * @param park 
     */
    public void setOrderToPlants(Park park) {
        if (park.getPlantCollection() != null) {
            for (Order order : orderCollection) {
                for (Plant plant : park.getPlantCollection()) {
                    if (order.getPlant().equals(plant)) {
                        plant.setOrder(order);
                    }
                }
            }
        }
    }

    /**
     * Get orders in specific park
     * @param park
     * @return 
     */
    public Collection<Order> getOrdersInPark(Park park) {
        ArrayList<Order> parkOrderCollection = null;//new ArrayList<Order>();
        if (orderCollection != null) {
            for (Order order : orderCollection) {
                if (order.getPlant().getPark().equals(park)) {
                    if (parkOrderCollection == null) {
                        parkOrderCollection = new ArrayList<Order>();
                    }
                    parkOrderCollection.add(order);
                }
            }
        }
        return parkOrderCollection;
    }
    /**
     * Get only executed orders
     * @return List of executed orders
     */
    public Collection<Order> getExecutedOrders() {
        ArrayList<Order> parkOrderCollection = null;
        if (orderCollection != null) {
            for (Order order : orderCollection) {
                if (order.getExecutionDate()!=null) {
                    if (parkOrderCollection == null) {
                        parkOrderCollection = new ArrayList<Order>();
                    }
                    parkOrderCollection.add(order);
                }
            }
        }
        return parkOrderCollection;
    }

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
    
    public Order getOrder(int orderId) {
        if (orderCollection != null) {
            for (Order order : orderCollection) {
                if (order.getOrderId() == orderId) {
                    return order;
                }
            }
        }
        return null;
    }

    public Collection<Park> getParkCollection() {
        if (parkCollection == null) {
            parkCollection = new ArrayList<Park>();
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
        hash += (ownerId != null ? ownerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Owner)) {
            return false;
        }
        Owner other = (Owner) object;
        if ((this.ownerId == null && other.ownerId != null) || (this.ownerId != null && !this.ownerId.equals(other.ownerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getUserInfo().getLogin() != null) {
            sb.append(getUserInfo().getLogin());
        }

        return sb.toString();
    }
}
