package ejbs;

import java.util.Collection;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Tables;

/**
 * Session Bean implementation class TablemManagerStateless
 */
@Stateless
@LocalBean
public class TablemManagerStateless implements TableManagerStatelessRemote {

	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public TablemManagerStateless() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Tables addTable(Tables table) {
		em.persist(table);	
		em.flush();
		return table;
	}

	@Override
	public Tables getTable(int tableId) {
		return em.find(Tables.class, tableId);
	}

	@Override
	public Collection<Tables> getTablesList() {
		return em.createQuery("SELECT m FROM Module m").getResultList();
	}

	@Override
	public Tables editTable(Tables table) {
		em.merge(table);
		em.flush();
		return table;		
	}
}
