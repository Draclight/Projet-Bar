package view;

import java.io.IOException;
import java.io.Serializable;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import controleur.EjbLocator;
import dtos.*;
import ejbs.*;

@Named("applicationBean")
@ApplicationScoped
public class ApplicationBean implements Serializable {
	private TableManagerStatelessRemote tableManager;
	private OrderManagerStatelessRemote orderManager;
	private StateManagerStatelessRemote stateManager;
	private Collection<TablesDto> lesTables = null;
	
	public ApplicationBean()
	{
		updateTables();
	}

	public void updateTables()
	{	
		System.out.println("Récupération du moduleManager");
		tableManager = EjbLocator.getLocator().getTableManager();
		if(tableManager != null)
		{
			lesTables = tableManager.getTablesList();		
		}
	}

	public Collection<TablesDto> getLesTables() 
	{
		return lesTables;
	}

	public void setLesTables(Collection<TablesDto> lesTables) 
	{
		this.lesTables = lesTables;
	}
	
	public void orderDelivered(int tableId) {
		tableManager = EjbLocator.getLocator().getTableManager();
		orderManager = EjbLocator.getLocator().getOrderManager();
		stateManager = EjbLocator.getLocator().getStateManager();
		OrderDto order = null;
		StateDto state = null;
		TablesDto table = tableManager.getTable(tableId);
		if (table != null) {
			order = table.getOrdersOfTable().stream().filter(o -> o.getOrderState().getStateId() == 2).findFirst().orElse(null);
		}
		
		if(order != null) {
			state = stateManager.getState(3);
			order.setOrderState(state);
			orderManager.editOrder(order);
		}
	}
}
