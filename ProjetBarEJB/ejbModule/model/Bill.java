package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Bills")
public class Bill implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private int billId;
	private String billAmount;
	
	public int getBillId() {
		return billId;
	}
	
	public void setBillId(int billId) {
		this.billId = billId;
	}
	
	public String getBillAmount() {
		return billAmount;
	}
	
	public void setBillAmount(String billAmount) {
		this.billAmount = billAmount;
	}

	@OneToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
	private Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
