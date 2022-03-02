package ejbs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.*;
import dtos.*;
import ejbs.*;

/**
 * Session Bean implementation class TableManagerStateless
 */
@Stateless
@LocalBean
public class TableManagerStateless implements TableManagerStatelessRemote {

	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public TableManagerStateless() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Tables addTable(Tables tables) {
		em.persist(tables);	
		em.flush();
		return tables;
	}

	@Override
	public TablesDto getTable(int tableId) {
		Tables t = em.find(Tables.class, tableId);
		TablesDto table = new TablesDto();
		
		if (t != null) {
			table.setTableId(t.getTableId());
			table.setEmpty(t.isEmpty());
			table.setEmpty(t.isBooked());
			table.setNbChair(t.getNbChair());
			List<OrderDto> ordersDto = new ArrayList<OrderDto>();
			for(Order o : t.getOrdersOfTable()) {
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
				ordersDto.add(orderDto);
			}
			
			table.setOrdersOfTable(ordersDto);
		}
		
		return table;
	}

	@Override
	public List<TablesDto> getTablesList() {
		List<TablesDto> tablesDto = new ArrayList<TablesDto>();
		List<Tables> tables = em.createQuery("SELECT t FROM Tables t").getResultList();
		
		for(Tables t : tables) {
			TablesDto table = new TablesDto();
			table.setTableId(t.getTableId());
			table.setEmpty(t.isEmpty());
			table.setEmpty(t.isBooked());
			table.setNbChair(t.getNbChair());
			List<OrderDto> ordersDto = new ArrayList<OrderDto>();
			for(Order o : t.getOrdersOfTable()) {
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
			}
			
			table.setOrdersOfTable(ordersDto);
		}
		
		return tablesDto; 
	}

	@Override
	public Tables editTable(Tables tables) {
		em.merge(tables);
		em.flush();
		return tables;		
	}
}
