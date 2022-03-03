package view;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import controleur.EjbLocator;
import dtos.*;
import ejbs.*;

@Named("newOrderBean")
@ApplicationScoped
public class NewOrderBean implements Serializable {
	private DrinkManagerStatelessRemote drinkManager;
	private OrderManagerStatelessRemote orderManager;
	private StateManagerStatelessRemote stateManager;
	private Collection<DrinkDto> lesDrinks = null;
	private List<DrinkDto> orderdDrinks = new ArrayList<DrinkDto>();
	private int tableId = 0;
	private OrderDto newOrder = new OrderDto();
	
	public NewOrderBean()
	{
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		tableId = Integer.parseInt(params.get("tableId"));
		newOrder = new OrderDto();
		
		updateDrinks();
	}

	public void updateDrinks()
	{	
		System.out.println("Récupération du drinksManager");
		drinkManager = EjbLocator.getLocator().getDrinkManager();
		
		if(drinkManager != null)
		{
			lesDrinks = drinkManager.getDrinksList();		
		} else {
			System.out.println("Erreur : newOrderBean - drinkManager is null");
		}
	}

	public Collection<DrinkDto> getLesDrinks() 
	{
		return lesDrinks;
	}

	public void setLesDrinks(Collection<DrinkDto> lesDrinks) 
	{
		this.lesDrinks = lesDrinks;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public OrderDto getNewOrder() {
		return newOrder;
	}

	public void setNewOrder(OrderDto newOrder) {
		this.newOrder = newOrder;
	}
	
	public List<DrinkDto> getOrderdDrinks() {
		return orderdDrinks;
	}

	public void setOrderdDrinks(List<DrinkDto> orderdDrinks) {
		this.orderdDrinks = orderdDrinks;
	}

	public void save() {
		stateManager = EjbLocator.getLocator().getStateManager();
		orderManager = EjbLocator.getLocator().getOrderManager();
		
		if(orderManager != null && stateManager != null)
		{
			TablesDto table = new TablesDto();
			table.setTableId(tableId);
			newOrder.setTableOfOrder(table);
			StateDto state = stateManager.getState(1);
			newOrder.setOrderState(state);
			double orderAmout = 0;
			for(DrinkDto d : lesDrinks) {
				if (d.getDrinkOrderedQuantity() > 0) {
					int qtyOrder = d.getDrinkOrderedQuantity();
					orderAmout += d.getDrinkPrice();
					newOrder.setOrderAmount(orderAmout);
					for (int i = 0; i <  d.getDrinkOrderedQuantity(); i++) {
						DrinkDto test = new DrinkDto();
						test.setDrinkId(d.getDrinkId());
						test.setDrinkName(d.getDrinkName());
						test.setDrinkOrderedQuantity(d.getDrinkOrderedQuantity());
						test.setDrinkPrice(d.getDrinkPrice());
						test.setDrinkQuantity(d.getDrinkQuantity());
						orderdDrinks.add(test);
					}
				}
			}
			newOrder.setDrinksOfOrder(orderdDrinks);
			
			orderManager.addOrder(newOrder);
			
			for(DrinkDto d : lesDrinks) {
				if (d.getDrinkOrderedQuantity() > 0) {
					d.setDrinkQuantity(d.getDrinkQuantity() - d.getDrinkOrderedQuantity());
					drinkManager.editDrink(d);
				}
			}
		} else {
			System.out.println("Erreur : newOrderBean - one manager is null");
		}
	}
}
