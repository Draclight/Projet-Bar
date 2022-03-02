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
import ejbs.*;
import dtos.*;

/**
 * Servlet implementation class SetOrderReady
 */
@WebServlet("/SetOrderReady")
public class SetOrderReady extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetOrderReady() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StateManagerStatelessRemote stateManager = null;
		OrderManagerStatelessRemote orderManager = null;
    	Context context;
    	final Hashtable jndiProperties = new Hashtable();
    	jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    	OrderDto order = null;
    	StateDto state = null;
    	
    	try {
    		// Connexion au serveur d'application (pas besoin de fichier properties suppl�mentaires ici. Le .EAR simplifie la discussion entre les composants
	    	context = new InitialContext(jndiProperties);
	    	// R�cup�ration d'un pointeur sur un ejb sessions sans �tat via son JNDI
			ObjectInputStream fluxentree = new ObjectInputStream(request.getInputStream());
			// R�cup�ration du r�sultat de la requ�te
			order = (OrderDto)fluxentree.readObject();

	    	stateManager = EjbLocator.getLocator().getStateManager();  
	    	orderManager = EjbLocator.getLocator().getOrderManager();  
			
			if (stateManager != null) {					
		    	state = stateManager.getState(2);						    	
			}else {
				System.out.println("Servlet 'SetOrderReady' - stateManager is null");				
			}
			
			if (state != null) {
				if (orderManager != null) {					
			    	order.setOrderState(state);
			    	orderManager.editOrder(order);
				}else {
					System.out.println("Servlet 'SetOrderReady' - orderManager is null");				
				}			    	
			}else {
				System.out.println("Servlet 'SetOrderReady' - state is null");				
			}
	    	
			// Pr�paration du flux de sortie
			ObjectOutputStream sortie=new ObjectOutputStream(response.getOutputStream());

			// Envoi du r�sultat au client
			sortie.writeObject(order);
		} catch (Exception ex) {
				System.out.println("Erreur d'ex�cution de la requ�te SQL : " + ex);
		}
	}

}
