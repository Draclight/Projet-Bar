package ejbs;

import java.util.Collection;
import javax.ejb.Remote;
import model.Drink;

@Remote
public interface DrinkManagerStatelessRemote {

	public Drink addDrink(Drink drink);
	public Drink getDrink(int drinkId);
	public Collection<Drink> getDrinksList();
	public Drink editDrink(Drink drink);
}
