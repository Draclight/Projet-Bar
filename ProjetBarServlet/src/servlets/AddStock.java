package servlets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controleur.EjbLocator;
import dtos.*;
import ejbs.*;
/**
 * Servlet implementation class AddStock
 */
@WebServlet("/AddStock")
public class AddStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DrinkManagerStatelessRemote drinkManager = null;
    	Context context;
    	final Hashtable jndiProperties = new Hashtable();
    	jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    	List<DrinkDto> drinks = null;
    	DrinkDto currentDrink = null;
    	
    	try {
    		// Connexion au serveur d'application (pas besoin de fichier properties supplémentaires ici. Le .EAR simplifie la discussion entre les composants
	    	context = new InitialContext(jndiProperties);
	    	
	    	// Récupération d'un pointeur sur un ejb sessions sans état via son JNDI
			ObjectInputStream fluxentree = new ObjectInputStream(request.getInputStream());

			// Récupération du résultat de la requête
			currentDrink = (DrinkDto)fluxentree.readObject();
			drinkManager = EjbLocator.getLocator().getDrinkManager();  
			
			if (drinkManager != null) {
				drinkManager.editDrink(currentDrink);
			}else {
				System.out.println("Servlet 'GetDrinks' - drinkManager is null");				
			}

			// Préparation du flux de sortie
			ObjectOutputStream sortie=new ObjectOutputStream(response.getOutputStream());
		} catch (Exception ex) {
				System.out.println("Erreur d'exécution de la requête SQL : " + ex);
		}
	}

}
