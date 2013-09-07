package ua.epam.projects.project4.model.commands;

import java.util.ArrayList;
import java.util.Date;
import ua.epam.projects.project4.model.dao.DAOFactory;
import ua.epam.projects.project4.model.dao.OrderDAO;
import ua.epam.projects.project4.model.entities.Forester;
import ua.epam.projects.project4.model.entities.Order;

/**
 * Provides execution orders by forester
 * @author Sergiy Tolok
 */
public class ExecuteOrderCommand extends Command{
    
    public static final String ORDER_SELECT = "order_select";

    public String perform() {
        //Get order index
        String orderSelectStr = req.getParameter(ORDER_SELECT);
        if(orderSelectStr==null){
            //Send message to forester
            req.getSession().setAttribute("message", "choose_order_before");
             return FORESTER_MAIN;
        }
        int orderSelect = Integer.parseInt(orderSelectStr);
        Forester forester = (Forester) req.getSession().getAttribute(FORESTER);
        Order order = ((ArrayList<Order>)forester.getOrderCollection()).get(orderSelect);
        //If order is already executed
        if(order.getExecutionDate()!=null){
            return FORESTER_MAIN;
        }
        else{
            order.setExecutionDate(new Date());
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
            OrderDAO orderDAO = daoFactory.getOrderDAO();
            //Execute order - update in data storage
            if(orderDAO.updateOrder(order)){
                //Send message to forester
                req.getSession().setAttribute("message", "order_executed");
                req.getSession().setAttribute(FORESTER, forester);
            }
        }
        return FORESTER_MAIN;
    }
    
}
