package ejbs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dtos.DrinkDto;
import model.Drinks;
import model.Tables;

/**
 * Session Bean implementation class TableManagerStateless
 */
@Stateless
@LocalBean
public class DrinkManagerStateless implements DrinkManagerStatelessRemote {

	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public DrinkManagerStateless() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Drinks addDrink(Drinks drink) {
		em.persist(drink);	
		em.flush();
		return drink;
	}

	@Override
	public Drinks getDrink(int drinkId) {
		return em.find(Drinks.class, drinkId);
	}

	@Override
	public List<DrinkDto> getDrinksList() {
		List<Drinks> drinks = null;
		Drinks ds = getDrink(1); 
		
		try {
			drinks = em.createQuery("SELECT d FROM Drinks d").getResultList();	
		} catch (Exception e) {
			System.out.println("erreyr : " + e.getMessage());
		}
		
		List<DrinkDto> drinksDto = new ArrayList<DrinkDto>();
		if (drinks != null) {
			for(Drinks d : drinks) {
				DrinkDto drink = new DrinkDto();
				drink.setDrinkId(d.getDrinkId());
				drink.setDrinkName(d.getDrinkName());
				drink.setDrinkPrice(d.getDrinkPrice());
				drink.setDrinkQuantity(d.getDrinkQuantity());
				drinksDto.add(drink);
			}
		}
		return drinksDto;
	}

	@Override
	public DrinkDto editDrink(DrinkDto drinkDto) {
		Drinks drink = new Drinks();
		drink.setDrinkId(drinkDto.getDrinkId());
		drink.setDrinkName(drinkDto.getDrinkName());
		drink.setDrinkPrice(drinkDto.getDrinkPrice());
		drink.setDrinkQuanity(drinkDto.getDrinkQuantity());
		em.merge(drink);
		em.flush();
		return drinkDto;		
	}
}
