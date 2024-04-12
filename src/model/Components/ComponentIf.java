package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;

public class ComponentIf extends Component{
	
	private String term1;
	private String term2;
	private String operand;
	private Component nextComponent;
	private Condition C;

	public ComponentIf(Component nextComponent1, Component nextComponent2, MemoryStorage memory) {
		super(nextComponent1, nextComponent2, memory);
		// TODO Auto-generated constructor stub
		
	}
	
	public void set(String term1, String term2, String operand) {
		this.term1 = term1;
		this.term2 = term2;
		this.operand = operand;
		this.C = new Condition(getMemory(), term1, operand, term2);
	}
	
	@Override
	public Object execute() throws Exceptions {
		if(C.resolve()) {
			nextComponent = getNextComponent1();
		}else {
			nextComponent = getNextComponent2();
		}
		return null;
	}
	
	@Override
	public Component getNextComponent() {
		return nextComponent;
	}

}
