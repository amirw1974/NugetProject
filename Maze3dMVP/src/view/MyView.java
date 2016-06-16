package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerator.Maze3d;
import algorithms.search.Solution;

public class MyView extends Observable implements View, Observer {

	private BufferedReader in;
	private Writer out;
	private CLI cli;
	MazeWindow mw;
	public MyView(BufferedReader in, Writer out) {
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

	public void displayCrossSection(int[][] crossS) {
		String s = new String();
		for (int j = 0; j < crossS.length; j++) {
			for (int j2 = 0; j2 < crossS[0].length; j2++) {
				s += " " + crossS[j][j2];
			}
			s += "\n";
		}
		System.out.println(s);

	}

	@Override
	public void displayMaze(Maze3d maze) {
		mw.displayMaze(maze);
	}
	@Override
	public void update(Observable o, Object arg) {
		if (o == cli) {
			setChanged();
			notifyObservers(arg);
		}

	}

	@Override
	public void displaySolution(Solution s) {
		try {
			out.write(s.toString() + "\n");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
