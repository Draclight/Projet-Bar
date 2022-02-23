package servlets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ejbs.*;
import model.*;

/**
 * Servlet implementation class TableServlet
 */
@WebServlet("/getTables")
public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TableManagerStatelessRemote tableManager = null;
    	Context context;
    	final Hashtable jndiProperties = new Hashtable();
    	jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

    	try {
    		// Connexion au serveur d'application (pas besoin de fichier properties supplémentaires ici. Le .EAR simplifie la discussion entre les composants
	    	context = new InitialContext(jndiProperties);
	    	// Récupération d'un pointeur sur un ejb sessions sans état via son JNDI

	    	tableManager = (TableManagerStatelessRemote)context.lookup("java:global/ProjetBarComplet/ProjetBarEJB/TableManagerStateless!ejbs.TableManagerStatelessRemote");        	
	    	    
	    	Collection<Tables> tables = tableManager.getTablesList();
			
			// Préparation du flux de sortie
			ObjectOutputStream sortie=new ObjectOutputStream(response.getOutputStream());
			
			// Envoi du résultat au client
			sortie.writeObject(tables);
		} catch (Exception ex) {
				System.out.println("Erreur d'exécution de la requête SQL : "+ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TableManagerStatelessRemote tableManager = null;
    	Context context;
    	final Hashtable jndiProperties = new Hashtable();
    	jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

    	try {
    		// Connexion au serveur d'application (pas besoin de fichier properties supplémentaires ici. Le .EAR simplifie la discussion entre les composants
	    	context = new InitialContext(jndiProperties);
	    	// Récupération d'un pointeur sur un ejb sessions sans état via son JNDI

	    	tableManager = (TableManagerStatelessRemote)context.lookup("java:global/ProjetBarComplet/ProjetBarEJB/TableManagerStateless!ejbs.TableManagerStatelessRemote");        	
	    	    
	    	Collection<Tables> tables = tableManager.getTablesList();
			
			// Préparation du flux de sortie
			ObjectOutputStream sortie=new ObjectOutputStream(response.getOutputStream());
			
			// Envoi du résultat au client
			sortie.writeObject(tables);
		} catch (Exception ex) {
				System.out.println("Erreur d'exécution de la requête SQL : "+ex);
		}
	}
}
