package model.Components;

import model.Memory.MemoryStorage;

public class ComponentStart extends AlgorithmComponent{

	private AlgorithmComponent nextComponent;
	
	public ComponentStart(AlgorithmComponent nextComponent) {
		super(nextComponent, null, null);
	}
	
	public void execute() {
		MemoryStorage MS = MemoryStorage.getInstance();
		super.setMemory(MS);
	}
}
