package model;

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
import java.util.Set;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private int orderId;
    private double orderAmount;
	
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

	@ManyToOne
    @JoinColumn(name ="stateId", referencedColumnName = "stateId")
	private State orderState;

	public State getOrderState() {
		return orderState;
	}

	public void setOrderState(State orderState) {
		this.orderState = orderState;
	}	
	
	@ManyToOne
    @JoinColumn(name ="tableId", referencedColumnName = "tableId")
	private Tables tableOfOrder;

	public Tables getTableOfOrder() {
		return tableOfOrder;
	}

	public void setTableOfOrder(Tables tableOfOrder) {
		this.tableOfOrder = tableOfOrder;
	}
	
	@ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "orderDetails", 
		joinColumns = @JoinColumn(name = "orderId", referencedColumnName = "orderId"), 
		inverseJoinColumns = @JoinColumn(name = "drinkId", referencedColumnName = "drinkId"))
	public Set<Drink> drinksOfOrder = new HashSet<Drink>();

	public Set<Drink> getDrinksOfOrder() {
		return drinksOfOrder;
	}

	public void setDrinksOfOrder(Set<Drink> drinksOfOrder) {
		this.drinksOfOrder = drinksOfOrder;
	}

}
