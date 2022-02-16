package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Drinks")
public class Drink implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private int drinkId;
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
	private String drinkName;
	private double drinkPrice;
}
