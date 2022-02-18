package ejbs;

import java.util.Collection;
import javax.ejb.Remote;
import model.Order;

@Remote
public interface OrderManagerStatelessRemote {

	public Order addOrder(Order order);
	public Order getOrder(int orderId);
	public Collection<Order> getOrdersList();
	public Order editOrder(Order order);
	public Collection<Order> getOrdersByTableId(int tableId);
}
