package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;

public class ComponentStart extends AlgorithmComponent{

	private AlgorithmComponent nextComponent;
	
	public ComponentStart(AlgorithmComponent nextComponent) {
		super(nextComponent, null, null);
	}

	@Override
	public void execute() throws Exceptions {
		// TODO Auto-generated method stub
		MemoryStorage MS = MemoryStorage.getInstance();
		super.setMemory(MS);
	}
	
	
}
