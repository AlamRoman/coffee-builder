package model.Memory;

import java.util.HashSet;
import java.util.Iterator;
import model.Exceptions;

public class MemoryStorage {
	
	HashSet<Variable> memory;
	private static MemoryStorage instance;

	public MemoryStorage() {
		super();
		this.memory = new HashSet<Variable>();
	}
	
	public static MemoryStorage getInstance() {
		instance = (instance==null) ? (new MemoryStorage()) : instance;
		return instance;
	}
	
	public void addVariable(Variable var) throws Exceptions{
		if(!memory.add(var)) {
			throw new Exceptions(Exceptions.EXISTING_VARIABLE);
		}
	}
	
	public Variable getVariableByName(String name) throws Exceptions{
		
		for (Variable temp : memory) {
			
			if(temp.getName().equals(name)) {
				return temp;
			}
			
		}
		
		throw new Exceptions(Exceptions.NON_EXISTING_VARIABLE);
		
	}
	
	
	
	
}
