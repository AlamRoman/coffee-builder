package model.Components;

import model.Memory.MemoryStorage;

public class ComponentComment extends Component{

	private String comment;

	public ComponentComment(Component nextComponent1, Component nextComponent2, MemoryStorage memory,
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

	@Override
	public Component getNextComponent() {
		return super.getNextComponent1();
	}

	@Override
	public String print() {
		String out = "COMMENT ";
		
		out += comment;
		
		return out;
	}
	
}
