package ejbs;

import java.util.Collection;
import java.util.List;

import dtos.*;
import javax.ejb.Remote;
import model.Tables;
@Remote
public interface TableManagerStatelessRemote {

	public Tables addTable(Tables tables);
	public TablesDto getTable(int tableId);
	public List<TablesDto> getTablesList();
	public Tables editTable(Tables tables);
	public TablesDto getOrderToPayByTableId(int tableId);
}
