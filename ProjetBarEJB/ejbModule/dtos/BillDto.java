package dtos;

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

public class BillDto implements Serializable {
    private int billId;
	private String billAmount;
	private OrderDto order;
	
	public BillDto() { }

	public BillDto(int billId, String billAmount, OrderDto order) {
		this.billId = billId;
		this.billAmount = billAmount;
		this.order = order;
	}

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

	public OrderDto getOrder() {
		return order;
	}

	public void setOrder(OrderDto order) {
		this.order = order;
	}
}
