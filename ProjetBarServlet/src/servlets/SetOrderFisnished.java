package servlets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controleur.EjbLocator;
import dtos.OrderDto;
import dtos.StateDto;
import ejbs.OrderManagerStatelessRemote;
import ejbs.StateManagerStatelessRemote;

/**
 * Servlet implementation class SetOrderFisnished
 */
@WebServlet("/SetOrderFisnished")
public class SetOrderFisnished extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetOrderFisnished() {
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
		OrderManagerStatelessRemote orderManager = null;
		StateManagerStatelessRemote stateManager = null;
		Context context;
    	final Hashtable jndiProperties = new Hashtable();
    	jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    	OrderDto order = null;
    	StateDto state = null;
    	int tableId = 0;
    	
    	try {
    		// Connexion au serveur d'application (pas besoin de fichier properties supplémentaires ici. Le .EAR simplifie la discussion entre les composants
	    	context = new InitialContext(jndiProperties);
	    	
	    	// Récupération d'un pointeur sur un ejb sessions sans état via son JNDI
			ObjectInputStream fluxentree = new ObjectInputStream(request.getInputStream());

			// Récupération du résultat de la requête
			order = (OrderDto)fluxentree.readObject();
			orderManager = EjbLocator.getLocator().getOrderManager();  
			stateManager = EjbLocator.getLocator().getStateManager();
			
			if (stateManager != null) {
				state = stateManager.getState(4);
				order.setOrderState(state);
			}
			
			if (orderManager != null) {
				order = orderManager.editOrder(order);
			}else {
				System.out.println("Servlet 'OrderToPay' - tableManager is null");				
			}
	    	
			// Préparation du flux de sortie
			ObjectOutputStream sortie=new ObjectOutputStream(response.getOutputStream());

			// Envoi du résultat au client
			sortie.writeObject(order);
		} catch (Exception ex) {
				System.out.println("Erreur d'exécution de la requête SQL : " + ex);
		}
	}

}
