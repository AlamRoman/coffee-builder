package model;

public class ID {

	private int _ID;
	
	public ID() {
		_ID = IDGenerator.generateID();
	}
	
	public int getID() {
		return _ID;
	}
	
}
