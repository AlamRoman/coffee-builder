package model.Components;

import model.Memory.MemoryStorage;

public class ComponentEnd extends Component{

	public ComponentEnd() {
		super(null, null, null);
	}
	
	public void execute() {
		MemoryStorage.destroyInstance();
	}
	
}
