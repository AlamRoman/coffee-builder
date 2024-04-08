package model;

import java.util.HashSet;

public class MemoryStorage {
	
	HashSet<Variable> memory;

	public MemoryStorage() {
		super();
		this.memory = new HashSet<Variable>();
	}
	
	public void addVariable(Variable var) throws Exceptions{
		if(!memory.add(var)) {
			throw new Exeptions
	}
	
	
	
}
