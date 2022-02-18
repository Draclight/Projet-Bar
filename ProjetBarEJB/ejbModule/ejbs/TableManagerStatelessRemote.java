package ejbs;

import java.util.Collection;

import javax.ejb.Remote;
import model.Tables;
@Remote
public interface TableManagerStatelessRemote {

	public Tables addTable(Tables tables);
	public Tables getTable(int tableId);
	public Collection<Tables> getTablesList();
	public Tables editTable(Tables tables);
}
