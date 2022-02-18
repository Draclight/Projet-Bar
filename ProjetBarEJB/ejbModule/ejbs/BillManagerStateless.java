package ejbs;

import java.util.Collection;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Bill;

/**
 * Session Bean implementation class TableManagerStateless
 */
@Stateless
@LocalBean
public class BillManagerStateless implements BillManagerStatelessRemote {

	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public BillManagerStateless() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Bill addBill(Bill bill) {
		em.persist(bill);	
		em.flush();
		return bill;
	}

	@Override
	public Bill getBill(int billId) {
		return em.find(Bill.class, billId);
	}

	@Override
	public Collection<Bill> getBillsList() {
		return em.createQuery("SELECT b FROM Bills b").getResultList();
	}

	@Override
	public Bill editBill(Bill bill) {
		em.merge(bill);
		em.flush();
		return bill;		
	}
}
