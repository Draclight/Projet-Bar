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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Tables")
public class Tables implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private int tableId;
    private int nbChair;
    private boolean isEmpty;
    private boolean isBooked;
	
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

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "tableId")
    private Set<Order> ordersOfTable = new HashSet<Order>();

	public Set<Order> getOrdersOfTable() {
		return ordersOfTable;
	}

	public void setOrdersOfTable(Set<Order> ordersOfTable) {
		this.ordersOfTable = ordersOfTable;
	}
	
}
