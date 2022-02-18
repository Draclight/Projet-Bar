package ejbs;

import java.util.Collection;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Order;

/**
 * Session Bean implementation class TableManagerStateless
 */
@Stateless
@LocalBean
public class OrderManagerStateless implements OrderManagerStatelessRemote {

	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public OrderManagerStateless() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Order addOrder(Order order) {
		em.persist(order);	
		em.flush();
		return order;
	}

	@Override
	public Order getOrder(int orderId) {
		return em.find(Order.class, orderId);
	}

	@Override
	public Collection<Order> getOrdersList() {
		return em.createQuery("SELECT o FROM Orders o").getResultList();
	}

	@Override
	public Order editOrder(Order order) {
		em.merge(order);
		em.flush();
		return order;		
	}

	@Override
	public Collection<Order> getOrdersByTableId(int tableId) {
		// TODO Auto-generated method stub
		return null;
	}
}
