package ejbs;

import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import dtos.*;
import model.*;
import java.util.List;

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
	public OrderDto addOrder(OrderDto order) {
		
		em.persist(order);	
		em.flush();
		return order;
	}

	@Override
	public OrderDto getOrder(int orderId) {
		Order o = em.find(Order.class, orderId);
		
		OrderDto orderDto = new OrderDto();
		orderDto.setOrderId(o.getOrderId());
		orderDto.setOrderAmount(o.getOrderAmount());
		StateDto stateDto = new StateDto();
		stateDto.setStateId(o.getOrderState().getStateId());
		stateDto.setStateName(o.getOrderState().getStateName());
		orderDto.setOrderState(stateDto);	
		List<DrinkDto> drinksDto = new ArrayList<DrinkDto>();
		for(Drink d : o.getDrinksOfOrder()) {
			DrinkDto drink = new DrinkDto();
			drink.setDrinkId(d.getDrinkId());
			drink.setDrinkName(d.getDrinkName());
			drink.setDrinkPrice(d.getDrinkPrice());
			drink.setDrinkQuantity(d.getDrinkQuantity());
			drinksDto.add(drink);
		}
		orderDto.setDrinksOfOrder(drinksDto);
		
		return orderDto;
	}

	@Override
	public Collection<Order> getOrdersList() {
		return em.createQuery("SELECT o FROM Orders o").getResultList();
	}

	@Override
	public OrderDto editOrder(OrderDto order) {
		Order o = em.find(Order.class, order.getOrderId());
		State state = new State();
		state.setStateId(order.getOrderState().getStateId());
		state.setStateName(order.getOrderState().getStateName());
		o.setOrderState(state);
		try {
			em.merge(o);
			em.flush();	
		} catch (Exception e) {
			System.out.println("Erreur edit order - " + e.getMessage());
		}
		return order;		
	}

	@Override
	public Collection<Order> getOrdersByTableId(int tableId) {
		return getOrdersList();
	}
}
