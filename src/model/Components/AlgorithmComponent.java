package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;

public class AlgorithmComponent {

	private AlgorithmComponent nextComponent1;
	private AlgorithmComponent nextComponent2;
	private MemoryStorage memory;
	
	public AlgorithmComponent(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory) {
		super();
		this.nextComponent1 = nextComponent1;
		this.nextComponent2 = nextComponent2;
		this.memory = memory;
	}
	
	public Object execute() throws Exceptions {
		return null;
	}
	
	public AlgorithmComponent getNextComponent() {
		return null;
	}
	
	public String print() {
		return null;
	}

	public AlgorithmComponent getNextComponent1() {
		return nextComponent1;
	}

	public void setNextComponent1(AlgorithmComponent nextComponent1) {
		this.nextComponent1 = nextComponent1;
	}

	public AlgorithmComponent getNextComponent2() {
		return nextComponent2;
	}

	public void setNextComponent2(AlgorithmComponent nextComponent2) {
		this.nextComponent2 = nextComponent2;
	}

	public MemoryStorage getMemory() {
		return memory;
	}

	public void setMemory(MemoryStorage memory) {
		this.memory = memory;
	}
	
	public String classSimpleName() {
		// TODO Auto-generated method stub
		return getClass().getSimpleName();
	}
	
	
	
	
	
}
