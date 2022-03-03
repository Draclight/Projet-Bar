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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

public class TablesDto implements Serializable{
    private int tableId;
    private int nbChair;
    private boolean isEmpty;
    private boolean isBooked;
    private List<OrderDto> ordersOfTable;

    public TablesDto() {}
    
    public TablesDto(int tableId, int nbChair, boolean isEmpty, boolean isBooked) {
		this.tableId = tableId;
		this.nbChair = nbChair;
		this.isEmpty = isEmpty;
		this.isBooked = isBooked;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public int getNbChair() {
		return nbChair;
	}

	public void setNbChair(int nbChair) {
		this.nbChair = nbChair;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public boolean getIsEmpty() {
		return isEmpty;
	}
	
	public boolean isBooked() {
		return isBooked;
	}

	public boolean getIsBooked() {
		return isBooked;
	}
	
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	
	public List<OrderDto> getOrdersOfTable() {
		return ordersOfTable;
	}

	public void setOrdersOfTable(List<OrderDto> ordersOfTable) {
		this.ordersOfTable = ordersOfTable;
	}
	
}
