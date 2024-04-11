package model.Components;

import model.Memory.MemoryStorage;

public class ComponentComment extends AlgorithmComponent{

	private String comment;

	public ComponentComment(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory,
			String comment) {
		super(nextComponent1, nextComponent2, memory);
		
		this.comment = comment;
	}

	public String execute() {
		return comment;
	}

	public void set(String comment) {
		this.comment = comment;
	}
	
}
