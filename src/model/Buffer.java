package model;

import java.util.ArrayList;

import controller.Controller;
import model.Components.ComponentComment;
import model.Memory.MemoryStorage;

/**
 * This class handles all the outputs sent during the execution of the algorithm when a {@link ComponentComment} gets executed.
 * It saves all the received outputs in a private variable that can be edited and read with two methods: {@link Buffer#write(String)} and
 * {@link Buffer#read()}
 * */
public class Buffer {

	private String s;
	
	/**<p>
	* The constructor method of the {@link Buffer} class
	* </p>
	* <p>
	* Creates an instance of {@link Buffer} and initializes its variable
	* </p>
	*/
	public Buffer() {
		s = "";
	}
	
	/**
	* <p>
	* This method appends a string to the existing variable in the buffer
	* </p>
	* @param s The string data from the output that has to be written in the buffer
	*/
	public void write(String s) {
		this.s += s + "\n";
	}
	
	/**
	* <p>
	* This method returns the current text contained in the buffer
	* </p>
	* @return {@link String}
	*/
	public String read() {
		return s;
	}

	/**
	* <p>
	* This method clears the string contained in the buffer
	* </p>
	*/
	public void clear() {
		s="";
		
	}
	
}
