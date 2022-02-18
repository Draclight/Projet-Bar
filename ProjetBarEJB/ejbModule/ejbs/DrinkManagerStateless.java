package ejbs;

import java.util.Collection;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Drink;

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
	public Drink addDrink(Drink drink) {
		em.persist(drink);	
		em.flush();
		return drink;
	}

	@Override
	public Drink getDrink(int drinkId) {
		return em.find(Drink.class, drinkId);
	}

	@Override
	public Collection<Drink> getDrinksList() {
		return em.createQuery("SELECT d FROM Drinks d").getResultList();
	}

	@Override
	public Drink editDrink(Drink drink) {
		em.merge(drink);
		em.flush();
		return drink;		
	}
}
