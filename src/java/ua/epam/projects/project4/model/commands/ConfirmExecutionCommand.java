package ua.epam.projects.project4.model.commands;

import java.util.ArrayList;
import java.util.Date;
import ua.epam.projects.project4.model.dao.DAOFactory;
import ua.epam.projects.project4.model.dao.OrderDAO;
import ua.epam.projects.project4.model.entities.Order;
import ua.epam.projects.project4.model.entities.Owner;

/**
 * Owner confirm order which have executed by forester
 *
 * @author Sergiy Tolok
 */
public class ConfirmExecutionCommand extends Command {

    public static final String ORDER_SELECT = "order_select";
    public static final String PAGE = "page";

    public String perform() {
        //Get page attribute which is send request
        String page = req.getSession().getAttribute(PAGE).toString();
        page = page.equals(PARK) ? PARK_TO_OWNER : OWNER_MAIN;
        //Get order id
        String orderSelectStr = req.getParameter(ORDER_SELECT);
        if (orderSelectStr == null) {
            //Send message to owner
            req.getSession().setAttribute(MESSAGE, "choose_order_before");
            return page;
        }
        int orderSelect = Integer.parseInt(orderSelectStr);
        Owner owner = (Owner) req.getSession().getAttribute(OWNER);
        //Get Order object from collection
        Order order = owner.getOrder(orderSelect);
        if (order.getExecutionDate() == null) {
            //if order is not executed yet - return to main page without any actions
            return page;
        } else {
            //Set confirm date as now date
            order.setConfirmDate(new Date());
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOType.MYSQL);
            OrderDAO orderDAO = daoFactory.getOrderDAO();
            //Update order object in data storage
            if (orderDAO.updateOrder(order)) {
                //Send message to owner
                req.getSession().setAttribute(MESSAGE, "order_confirmed");
                ((ArrayList<Order>) owner.getOrderCollection()).remove(order);
                if (page.equals(PARK_TO_OWNER)) {
                    order.getPlant().getPark().getPlant(order.getPlant().getPlantId()).setOrder(null);
                    req.getSession().setAttribute(ORDERS, owner.getOrdersInPark(order.getPlant().getPark()));
                }
                req.getSession().setAttribute(OWNER, owner);

            }
        }

        return page;
    }
}
