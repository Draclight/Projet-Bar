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
import controleur.*;
import ejbs.*;
import dtos.*;
/**
 * Servlet implementation class Pay
 */
@WebServlet("/Pay")
public class Pay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pay() {
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
		BillManagerStatelessRemote billManager = null;
    	Context context;
    	final Hashtable jndiProperties = new Hashtable();
    	jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    	BillDto bill = null;
    	
    	try {
    		// Connexion au serveur d'application (pas besoin de fichier properties supplémentaires ici. Le .EAR simplifie la discussion entre les composants
	    	context = new InitialContext(jndiProperties);
	    	
	    	// Récupération d'un pointeur sur un ejb sessions sans état via son JNDI
			ObjectInputStream fluxentree = new ObjectInputStream(request.getInputStream());

			// Récupération du résultat de la requête
			bill = (BillDto)fluxentree.readObject();
			billManager = EjbLocator.getLocator().getBillManager();  
			
			if (billManager != null) {
				bill = billManager.addBill(bill);
			}else {
				System.out.println("Servlet 'Pay' - orderManager is null");				
			}
	    	
			// Préparation du flux de sortie
			ObjectOutputStream sortie=new ObjectOutputStream(response.getOutputStream());

			// Envoi du résultat au client
			sortie.writeObject(bill);
		} catch (Exception ex) {
				System.out.println("Erreur d'exécution de la requête SQL : " + ex);
		}
	}

}
