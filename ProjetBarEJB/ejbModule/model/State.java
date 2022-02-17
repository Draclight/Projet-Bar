package model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "States")
public class State implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private int stateId;
	private String stateName;
	
	public int getStateId() {
		return stateId;
	}
	
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	
	public String getStateName() {
		return stateName;
	}
	
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "orderId")
    private Set<Order> ordersOfState = new HashSet<Order>();

	public Set<Order> getOrdersOfState() {
		return ordersOfState;
	}

	public void setOrdersOfState(Set<Order> ordersOfState) {
		ordersOfState = ordersOfState;
	}
}
