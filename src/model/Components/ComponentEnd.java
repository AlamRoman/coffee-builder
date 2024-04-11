package model.Components;

import model.Memory.MemoryStorage;

public class ComponentEnd extends Component{

	public ComponentEnd() {
		super(null, null, null);
	}
	
	public Object execute() {
		MemoryStorage.destroyInstance();
		return null;
	}
	
	public Component getNextComponent() {
		return null;
	}
	
}
