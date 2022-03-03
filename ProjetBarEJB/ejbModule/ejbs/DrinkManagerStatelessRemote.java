package ejbs;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;
import model.*;
import dtos.*;
@Remote
public interface DrinkManagerStatelessRemote {

	public Drinks addDrink(Drinks drink);
	public Drinks getDrink(int drinkId);
	public List<DrinkDto> getDrinksList();
	public DrinkDto editDrink(DrinkDto drink);
}
