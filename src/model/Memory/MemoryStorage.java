package model.Memory;

import java.util.HashSet;
import java.util.Iterator;

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
	
	public Variable getVariable(String name) throws Exceptions{
		
		for (Variable temp : memory) {
			
			if(temp.getName().equals(name)) {
				return temp;
			}
			
		}
		
		throw new Exceptions(Exceptions.NON_EXISTING_VARIABLE);
		
	}
	
	
	
	
}
