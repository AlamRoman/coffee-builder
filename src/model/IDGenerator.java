package model;

public class IDGenerator {
	
	private static int currentID;
	private static IDGenerator instance;

	private IDGenerator() {
		currentID = 0;
	}
	
	public static IDGenerator getInstance() {
		instance = (instance==null) ? (new IDGenerator()) : instance;
		return instance;
	}
	
	public static int generateID() {
		// TODO Auto-generated method stub
		return currentID++;
	}

}
