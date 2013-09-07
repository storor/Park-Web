package ua.tolok.projects.project4.model.commands;

import java.util.ArrayList;
import java.util.Date;
import ua.tolok.projects.project4.model.dao.DAOFactory;
import ua.tolok.projects.project4.model.dao.OrderDAO;
import ua.tolok.projects.project4.model.entities.Order;
import ua.tolok.projects.project4.model.entities.Owner;
import ua.tolok.projects.project4.model.entities.Park;
import ua.tolok.projects.project4.model.entities.Plant;
import ua.tolok.projects.project4.model.entities.PlantInfo;
import ua.tolok.projects.project4.model.entities.Service;

/**
 * Create order for plant new plant
 * @author Sergiy Tolok
 */
public class CreatePlantOrderCommand extends Command{
    
    public static final String PLANT_INFO_SELECT = "plant_info_select";
    
    public String perform() {
        //Get owner of order
        Owner owner = (Owner) req.getSession().getAttribute(OWNER);
        Plant plant = new Plant();
        //Get plant description
        PlantInfo plantInfo = ((ArrayList<PlantInfo>)req.getSession().getAttribute(PLANT_INFO_COLLECTION))
                .get(Integer.parseInt(req.getParameter(PLANT_INFO_SELECT)));
        plant.setPlantInfo(plantInfo);
        //Get park
        Park park = (Park) req.getSession().getAttribute(PARK);
        plant.setPark(park);
        Service service = CommandUtility.getServiseByName(
                (ArrayList<Service>)req.getSession().getAttribute(SERVICES),
                Service.PLANT);
        
        //Create new order
        Order order = new Order();
        order.setPlant(plant);
        order.setOwner(owner);
        order.setDateAdded(new Date());
        order.setForester(park.getForester());
        order.setService(service);
        
        //Insert order to data storage
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
        OrderDAO orderDAO = daoFactory.getOrderDAO();
        int insertOrder = orderDAO.insertOrder(order);
        if(insertOrder>0){
            order.setOrderId(insertOrder);
            
            if(owner.getOrderCollection()==null){
                    owner.setOrderCollection(new ArrayList<Order>());
                }
                //Add order to current owner
                owner.getOrderCollection().add(order);
                req.getSession().setAttribute(MESSAGE, "order_added");
                req.getSession().setAttribute(OWNER, owner);
                req.getSession().setAttribute(ORDERS, owner.getOrdersInPark(park));
        }
        return PARK_TO_OWNER;
    }
}
