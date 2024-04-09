package model.Memory;

import java.util.HashSet;

import model.Exceptions;

public class MemoryStorage {
	
	HashSet<Variable> memory;

	public MemoryStorage() {
		super();
		this.memory = new HashSet<Variable>();
	}
	
	public void addVariable(Variable var) throws Exceptions{
		if(!memory.add(var)) {
			throw new Exceptions(Exceptions.EXISTING_VARIABLE);
		}
	}
	
	
	
}
