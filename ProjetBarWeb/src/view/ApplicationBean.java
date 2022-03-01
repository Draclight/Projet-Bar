package view;

import java.io.Serializable;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import controleur.EjbLocator;
import model.TableManagerRemote;
import model.Tables;

@Named("applicationBean")
@ApplicationScoped
public class ApplicationBean implements Serializable {
	private TableManagerRemote tableManager;
	private Collection<model.Tables> lesTables = null;
	
	public ApplicationBean()
	{
		updateTables();
	}

	public void updateTables()
	{	
		System.out.println("Récupération du tableManager");
		tableManager = EjbLocator.getLocator().getTableManager();
		
		if(tableManager != null)
		{
			System.out.println("tableManager récup");
			lesTables = tableManager.getTablesList();			
		}else {
			System.out.println("tableManager non récup");
		}
	}

	public Collection<model.Tables> getLesModules() 
	{
		return lesTables;
	}

	public void setLesModules(Collection<model.Tables> lesTables) 
	{
		this.lesTables = lesTables;
	}
	
	public void nouvelleCommande(int tableId) {
		
	}
}
