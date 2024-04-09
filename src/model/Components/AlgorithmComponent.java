package model.Components;

public class AlgorithmComponent {

	private AlgorithmComponent nextComponent1;
	private AlgorithmComponent nextComponent2;
	
	public AlgorithmComponent(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2) {
		super();
		this.nextComponent1 = nextComponent1;
		this.nextComponent2 = nextComponent2;
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
	
	
	
	
	
}
