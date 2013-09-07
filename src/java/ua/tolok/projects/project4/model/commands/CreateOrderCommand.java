package ua.tolok.projects.project4.model.commands;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import ua.tolok.projects.project4.model.dao.DAOFactory;
import ua.tolok.projects.project4.model.dao.OrderDAO;
import ua.tolok.projects.project4.model.entities.Order;
import ua.tolok.projects.project4.model.entities.Owner;
import ua.tolok.projects.project4.model.entities.Park;
import ua.tolok.projects.project4.model.entities.Plant;
import ua.tolok.projects.project4.model.entities.Service;

/**
 * Create new order by owner
 * @author Sergiy Tolok
 */
public class CreateOrderCommand extends Command {

    public static final String PLANT_SELECT = "plant_select";
    public static final String SERVICE_SELECT = "service_select";

    public String perform() {
        //Get owner
        Owner owner = (Owner) req.getSession().getAttribute(OWNER);
        //Get order id
        String plantSelectStr = req.getParameter(PLANT_SELECT);
        if (plantSelectStr == null) {
            //Send message to owner
            req.getSession().setAttribute(MESSAGE, "choose_plant_before");
            return PARK_TO_OWNER;
        }
        int plant_selected = Integer.parseInt(req.getParameter(PLANT_SELECT));
        //Get park
        Park park = (Park) req.getSession().getAttribute(PARK);
        //Get plant
        Plant plant = ((ArrayList<Plant>) park.getPlantCollection()).get(plant_selected);
        int service_selected = Integer.parseInt(req.getParameter(SERVICE_SELECT));
        //Get service
        Service service = ((ArrayList<Service>)req.getSession().getAttribute(SERVICES)).get(service_selected);
        
        //Create new Order
        Order order = new Order();
        order.setOwner(owner);
        order.setPlant(plant);
        order.setDateAdded(new Date());
        order.setForester(park.getForester());
        order.setService(service);
        
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
        OrderDAO orderDAO = daoFactory.getOrderDAO();
        //Save to data storage
        int insertOrder = orderDAO.insertOrder(order);
        if(insertOrder>0){
            order.setOrderId(insertOrder);
            
            if(owner.getOrderCollection()==null){
                    owner.setOrderCollection(new ArrayList<Order>());
                }
                //Add to Owner Order Collection
                owner.getOrderCollection().add(order);
                owner.setOrderToPlants(park);
                //Send message to user for order added
                req.getSession().setAttribute(MESSAGE, "order_added");
                req.getSession().setAttribute(OWNER, owner);
                //Apdate list of Orders in Park
                req.getSession().setAttribute(ORDERS, owner.getOrdersInPark(park));
        }
        
        return PARK_TO_OWNER;
    }
}
