package ejbs;

import java.util.Collection;
import javax.ejb.Remote;
import model.Bill;

@Remote
public interface BillManagerStatelessRemote {

	public Bill addBill(Bill bill);
	public Bill getBill(int billId);
	public Collection<Bill> getBillsList();
	public Bill editBill(Bill bill);
	public Bill getBillByOrderId(int orderId);
}
