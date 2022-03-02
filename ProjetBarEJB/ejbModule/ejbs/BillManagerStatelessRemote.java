package ejbs;

import java.util.Collection;
import javax.ejb.Remote;
import dtos.*;

@Remote
public interface BillManagerStatelessRemote {

	public BillDto addBill(BillDto bill);
	public BillDto getBill(int billId);
	public Collection<BillDto> getBillsList();
	public BillDto editBill(BillDto bill);
	public BillDto getBillByOrderId(int orderId);
}
