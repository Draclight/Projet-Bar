package ejbs;

import java.util.Collection;
import javax.ejb.Remote;
import model.State;
import dtos.*;

@Remote
public interface StateManagerStatelessRemote {

	public State addState(State State);
	public StateDto getState(int StateId);
	public Collection<State> getStatesList();
	public State editState(State State);
}
