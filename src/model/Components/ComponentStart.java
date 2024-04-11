package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;

public class ComponentStart extends Component{

	private Component nextComponent;
	
	public ComponentStart(Component nextComponent) {
		super(nextComponent, null, null);
	}

	@Override
	public Object execute() throws Exceptions {
		// TODO Auto-generated method stub
		MemoryStorage MS = MemoryStorage.getInstance();
		super.setMemory(MS);
		return  null;
	}
	public Component getNextComponent() {
		return super.getNextComponent1();
	}
	
	
}
