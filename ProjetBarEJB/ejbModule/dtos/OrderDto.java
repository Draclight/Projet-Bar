package dtos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderDto implements Serializable {
    private int orderId;
    private double orderAmount;
	private StateDto orderState;
	private TablesDto tableOfOrder;
	public List<DrinkDto> drinksOfOrder;
	
    public OrderDto() {}
	
	public OrderDto(int orderId, double orderAmount, StateDto orderState, TablesDto tableOfOrder) {
		this.orderId = orderId;
		this.orderAmount = orderAmount;
		this.orderState = orderState;
		this.tableOfOrder = tableOfOrder;
		this.drinksOfOrder = drinksOfOrder;
	}

	public int getOrderId() {
    	return orderId;
    }

    public void setOrderId(int orderId) {
    	this.orderId = orderId;
	}
    
	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public StateDto getOrderState() {
		return orderState;
	}

	public void setOrderState(StateDto orderState) {
		this.orderState = orderState;
	}	

	public TablesDto getTableOfOrder() {
		return tableOfOrder;
	}

	public void setTableOfOrder(TablesDto tableOfOrder) {
		this.tableOfOrder = tableOfOrder;
	}

	public List<DrinkDto> getDrinksOfOrder() {
		return drinksOfOrder;
	}

	public void setDrinksOfOrder(List<DrinkDto> drinksOfOrder) {
		this.drinksOfOrder = drinksOfOrder;
	}

}
