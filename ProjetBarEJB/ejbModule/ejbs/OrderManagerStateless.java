package ejbs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import dtos.*;
import model.*;
import java.util.List;
import java.util.Set;

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
	public OrderDto addOrder(OrderDto orderDto) {
		Order order = new Order();
		order.setOrderAmount(orderDto.getOrderAmount());
		State state = new State();
		state.setStateId(orderDto.getOrderState().getStateId());
		state.setStateName(orderDto.getOrderState().getStateName());
		order.setOrderState(state);
		Tables table = new Tables();
		table.setTableId(orderDto.getTableOfOrder().getTableId());
		order.setTableOfOrder(table);
		Set<Drinks> drinks = new HashSet<Drinks>();
		for(DrinkDto d : orderDto.getDrinksOfOrder()) {
			Drinks drink = new Drinks();
			drink.setDrinkId(d.getDrinkId()); 
			drinks.add(drink);
		}
		order.setDrinksOfOrder(drinks);
		em.merge(order);	
		em.flush();
		return orderDto;
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
		for(Drinks d : o.getDrinksOfOrder()) {
			DrinkDto drink = new DrinkDto();
			drink.setDrinkId(d.getDrinkId());
			drink.setDrinkName(d.getDrinkName());
			drink.setDrinkPrice(d.getDrinkPrice());
			drink.setDrinkQuantity(d.getDrinkQuantity());
			drinksDto.add(drink);
		}
		orderDto.setDrinksOfOrder(drinksDto);
		TablesDto tableDto = new TablesDto();
		tableDto.setTableId(o.getTableOfOrder().getTableId());
		tableDto.setBooked(o.getTableOfOrder().isBooked());
		tableDto.setEmpty(o.getTableOfOrder().isEmpty());
		tableDto.setNbChair(o.getTableOfOrder().getNbChair());
		orderDto.setTableOfOrder(tableDto);
		return orderDto;
	}

	@Override
	public Collection<Order> getOrdersList() {
		return em.createQuery("SELECT o FROM Orders o").getResultList();
	}

	@Override
	public OrderDto editOrder(OrderDto order) {
		Order o = em.find(Order.class, order.getOrderId());
		o.setOrderAmount(order.getOrderAmount());
		Tables t = new Tables();
		t.setTableId(order.getTableOfOrder().getTableId());
		o.setTableOfOrder(t);
		List<Drinks> drinks = new ArrayList<Drinks>();
		for(var d : order.getDrinksOfOrder()) {
			Drinks drink = new Drinks();
			drink.setDrinkId(d.getDrinkId());
			drink.setDrinkName(d.getDrinkName());
			drink.setDrinkPrice(d.getDrinkPrice());
			drink.setDrinkQuanity(d.getDrinkQuantity());
			drinks.add(drink);
		}
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
	
	public OrderDto getOrderToPayByTableId(int tableId) {
		/*OrderDto ret = new OrderDto();		
		ret = getTable(tableId);
		if (ret != null) {
			List<OrderDto> ordersDto = new ArrayList<OrderDto>();
			for(OrderDto o : ret.getOrdersOfTable()) {
				if (o.getOrderState().getStateId() == 3) {
					ordersDto.add(o);
				}
			}
		}*/

		TypedQuery<Order> tp = em.createQuery("SELECT o FROM Orders o INNER JOIN o.tableId t WHERE t.tableId = :id", Order.class);
		tp.setParameter("id", tableId);
		Order o = tp.getSingleResult();
		
		OrderDto orderDto = new OrderDto();
		orderDto.setOrderId(o.getOrderId());
		orderDto.setOrderAmount(o.getOrderAmount());
		StateDto stateDto = new StateDto();
		stateDto.setStateId(o.getOrderState().getStateId());
		stateDto.setStateName(o.getOrderState().getStateName());
		orderDto.setOrderState(stateDto);	
		List<DrinkDto> drinksDto = new ArrayList<DrinkDto>();
		for(Drinks d : o.getDrinksOfOrder()) {
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
}
