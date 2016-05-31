package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerator.Maze3d;



public class MyView extends Observable implements View, Observer {

	private BufferedReader in;
	private Writer out;
	private CLI cli;
		
	public MyView(BufferedReader in, Writer out)
	{		
		this.in = in;
		this.out = out;	
		cli = new CLI(in, out);
		cli.addObserver(this);		
	}
			
	@Override
	public void displayMessage(String message) {
		try {
			out.write(message);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void start() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {				
				cli.start();
			}
			
		});	
		thread.start();
	}

	
	@Override
	public void displayMaze(Maze3d maze) {
		try {			
			out.write(maze.toString());
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == cli) {
			setChanged();
			notifyObservers(arg);
		}
		
	}
}
