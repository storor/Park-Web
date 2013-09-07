package ua.tolok.projects.project4.model.dao;

import java.util.ArrayList;
import ua.tolok.projects.project4.model.entities.Forester;
import ua.tolok.projects.project4.model.entities.Order;
import ua.tolok.projects.project4.model.entities.Owner;

/**
 * Oparating whith Order in data storage
 * @author Sergiy Tolok
 */
public interface OrderDAO {
    /**
     * Save to data storage new instance of Order
     * @param order new instance of Order
     * @return OrderId
     */
    public int insertOrder(Order order); 
    /**
     * Load list of orders for Owner
     * @param owner Owner which is owe orders
     * @param activeOnly Only not confirmed
     * @return list of orders for Owner
     */
    public ArrayList<Order> getAllOrders(Owner owner, boolean activeOnly);
    /**
     * Load list of orders for Forester
     * @param forester Forester which is owe orders
     * @param activeOnly Only not confirmed  
     * @return list of orders for Owner
     */
    public ArrayList<Order> getAllOrders(Forester forester, boolean activeOnly);
    /**
     * Update order information
     * @param order 
     * @return 
     */
    public boolean updateOrder(Order order); 
}
