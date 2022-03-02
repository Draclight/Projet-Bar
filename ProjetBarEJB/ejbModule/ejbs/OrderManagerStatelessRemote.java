package ejbs;

import java.util.Collection;
import javax.ejb.Remote;
import model.Order;
import dtos.*;
@Remote
public interface OrderManagerStatelessRemote {

	public OrderDto addOrder(OrderDto order);
	public OrderDto getOrder(int orderId);
	public Collection<Order> getOrdersList();
	public OrderDto editOrder(OrderDto order);
	public Collection<Order> getOrdersByTableId(int tableId);
}
