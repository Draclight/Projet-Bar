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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Drinks")
public class Drinks implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private int drinkId;
	private String drinkName;
	private double drinkPrice;
	private int drinkQuantity;

	public Drinks() { }
	
	public Drinks(int drinkId, String drinkName, double drinkPrice, int drinkQuantity) {
		this.drinkId = drinkId;
		this.drinkName = drinkName;
		this.drinkPrice = drinkPrice;
		this.drinkQuantity = drinkQuantity;
	}

	public int getDrinkId() {
		return drinkId;
	}
	
	public void setDrinkId(int drinkId) {
		this.drinkId = drinkId;
	}
	
	public String getDrinkName() {
		return drinkName;
	}
	
	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}
	
	public double getDrinkPrice() {
		return drinkPrice;
	}
	
	public void setDrinkPrice(double drinkPrice) {
		this.drinkPrice = drinkPrice;
	}
	
	public int getDrinkQuantity() {
		return drinkQuantity;
	}
	
	public void setDrinkQuanity(int qty) {
		this.drinkQuantity = qty;
	}
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER, mappedBy = "drinksOfOrder")
	private Set<Order> ordersOfDrink = new HashSet<Order>();

	public Set<Order> getOrdersOfDrink() {
		return ordersOfDrink;
	}

	public void setOrdersOfDrink(Set<Order> ordersOfDrink) {
		this.ordersOfDrink = ordersOfDrink;
	}
	
}
