package dtos;


import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
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

public class StateDto implements Serializable {
    private int stateId;
	private String stateName;
    private List<OrderDto> ordersOfState;
    
	public StateDto() { }
	
	public StateDto(int stateId, String stateName) {
		this.stateId = stateId;
		this.stateName = stateName;
	}

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

	public List<OrderDto> getOrdersOfState() {
		return ordersOfState;
	}

	public void setOrdersOfState(List<OrderDto> ordersOfState) {
		this.ordersOfState = ordersOfState;
	}
}
