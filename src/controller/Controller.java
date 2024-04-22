package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;

import model.AlgorithmExecuter;
import model.Buffer;
import model.Timer;
import model.Memory.MemoryStorage;

public abstract class Controller implements ActionListener, ItemListener, MouseListener {
	
	protected AlgorithmExecuter algorithmExecuter;
	protected MemoryStorage memory;
	protected Timer timer;
	protected Buffer buffer;

	public Controller(AlgorithmExecuter ALGORITHM_EXECUTER, Timer TIMER, Buffer buffer, MemoryStorage MEMORY) {
		// TODO Auto-generated constructor stub
		this.algorithmExecuter = ALGORITHM_EXECUTER;
		this.timer = TIMER;
		this.memory = MEMORY;
		this.buffer = buffer;
	}
	
	public AlgorithmExecuter getExecuter() {
		return algorithmExecuter;
	}
	
	public MemoryStorage getMemory() {
		return memory;
	}

	@Override
	public abstract void actionPerformed(ActionEvent e);
	
	@Override
	public abstract void itemStateChanged(ItemEvent e);

	public Timer getTimer() {
		// TODO Auto-generated method stub
		return this.timer;
	}

}
