package servlets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;
import controleur.EjbLocator;
import ejbs.*;
import dtos.*;

/**
 * Servlet implementation class OrderToPay
 */
@WebServlet("/OrderToPay")
public class OrderToPay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderToPay() {
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
		TableManagerStatelessRemote tableManager = null;
		Context context;
    	final Hashtable jndiProperties = new Hashtable();
    	jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    	TablesDto table = null;
    	int tableId = 0;
    	
    	try {
    		// Connexion au serveur d'application (pas besoin de fichier properties supplémentaires ici. Le .EAR simplifie la discussion entre les composants
	    	context = new InitialContext(jndiProperties);
	    	
	    	// Récupération d'un pointeur sur un ejb sessions sans état via son JNDI
			ObjectInputStream fluxentree = new ObjectInputStream(request.getInputStream());

			// Récupération du résultat de la requête
			tableId = (int)fluxentree.readObject();
			tableManager = EjbLocator.getLocator().getTableManager();
			
			if (tableManager != null) {
				table = tableManager.getOrderToPayByTableId(tableId);
			} else {
				System.out.println("Servlet 'OrderToPay' - tableManager is null");				
			}
	    	
			// Préparation du flux de sortie
			ObjectOutputStream sortie=new ObjectOutputStream(response.getOutputStream());

			// Envoi du résultat au client
			sortie.writeObject(table);
		} catch (Exception ex) {
				System.out.println("Erreur d'exécution de la requête SQL : " + ex);
		}
	}

}
