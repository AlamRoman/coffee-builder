package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;

public abstract class Component {

	private Component nextComponent1;
	private Component nextComponent2;
	private MemoryStorage memory;
	
	public Component(Component nextComponent1, Component nextComponent2, MemoryStorage memory) {
		super();
		this.nextComponent1 = nextComponent1;
		this.nextComponent2 = nextComponent2;
		this.memory = memory;
	}
	
	public abstract Object execute() throws Exceptions;

	public Component getNextComponent1() {
		return nextComponent1;
	}

	public void setNextComponent1(Component nextComponent1) {
		this.nextComponent1 = nextComponent1;
	}

	public Component getNextComponent2() {
		return nextComponent2;
	}

	public void setNextComponent2(Component nextComponent2) {
		this.nextComponent2 = nextComponent2;
	}

	public MemoryStorage getMemory() {
		return memory;
	}

	public void setMemory(MemoryStorage memory) {
		this.memory = memory;
	}
	
	
	
	
	
}
