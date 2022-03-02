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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

public class DrinkDto implements Serializable {
    private int drinkId;
	private String drinkName;
	private double drinkPrice;
	private int drinkQuantity;
	private List<OrderDto> ordersOfDrink;
	
	public DrinkDto() { }
	
	public DrinkDto(int drinkId, String drinkName, double drinkPrice, int drinkQuantity, List<OrderDto> ordersOfDrink) {
		this.drinkId = drinkId;
		this.drinkName = drinkName;
		this.drinkPrice = drinkPrice;
		this.drinkQuantity = drinkQuantity;
		this.ordersOfDrink = ordersOfDrink;
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
	
	public void setDrinkQuantity(int drinkQuantity) {
		this.drinkQuantity = drinkQuantity;
	}

	public List<OrderDto> getOrdersOfDrink() {
		return ordersOfDrink;
	}

	public void setOrdersOfDrink(List<OrderDto> ordersOfDrink) {
		this.ordersOfDrink = ordersOfDrink;
	}
}
