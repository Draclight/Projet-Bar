package ejbs;

import java.util.Collection;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Tables;

/**
 * Session Bean implementation class TableManagerStateless
 */
@Stateless
@LocalBean
public class TableManagerStateless implements TableManagerStatelessRemote {

	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public TableManagerStateless() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Tables addTable(Tables tables) {
		em.persist(tables);	
		em.flush();
		return tables;
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
	public Tables editTable(Tables tables) {
		em.merge(tables);
		em.flush();
		return tables;		
	}
}
