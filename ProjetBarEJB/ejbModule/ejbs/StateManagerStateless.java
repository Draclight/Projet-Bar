package ejbs;

import java.util.Collection;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.State;
/**
 * Session Bean implementation class TableManagerStateless
 */
@Stateless
@LocalBean
public class StateManagerStateless implements StateManagerStatelessRemote {

	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public StateManagerStateless() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public State addState(State state) {
		em.persist(state);	
		em.flush();
		return state;
	}

	@Override
	public State getState(int stateId) {
		return em.find(State.class, stateId);
	}

	@Override
	public Collection<State> getStatesList() {
		return em.createQuery("SELECT s FROM States s").getResultList();
	}

	@Override
	public State editState(State state) {
		em.merge(state);
		em.flush();
		return state;		
	}
}
