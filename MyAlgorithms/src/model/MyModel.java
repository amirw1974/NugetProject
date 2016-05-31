package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import algorithms.demo.MazeAdapter;
import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.MyMaze3dGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DFS;
import algorithms.search.Solution;
import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class MyModel implements Model {

	private Controller controller;
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	private HashMap<String, Maze3d> mazes = new HashMap<String, Maze3d>();
	private HashMap<String, Solution> solutions = new HashMap<>();

	public MyModel(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void generateMaze(String name, int rows, int cols, int levels) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				MyMaze3dGenerator mg = new MyMaze3dGenerator();
				Maze3d maze = mg.generate(rows, cols, levels);
				mazes.put(name, maze);
				controller.displayMessage("Maze " + name + " is ready\n");
			}
		});
		thread.start();
		threads.add(thread);
	}

	public Maze3d getMaze(String name) {
		return mazes.get(name);
	}

	@Override
	public void saveMaze(String name, String fileName) {
		if (!mazes.containsKey(name)) {
			controller.displayMessage("Maze " + name + " does not exist\n");
			return;
		}
		Maze3d maze = mazes.get(name);
		try {
			OutputStream out = new MyCompressorOutputStream(new FileOutputStream(fileName));
			byte[] bytes = maze.toByteArray();
			out.write(bytes.length);
			out.write(bytes);
			out.flush();
			out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void loadMaze(String fileName, String name) {
		try {
			InputStream in = new MyDecompressorInputStream(new FileInputStream(new File(fileName)));
			byte b[] = new byte[in.read()];
			in.read(b);
			mazes.put(name, new Maze3d(b));
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public Maze3d display(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public void SolveMaze(String name, String algorithm) {
		Maze3d maze;
		maze = mazes.get(name);
		Solution s;
		// TODO: Add if
		if (solutions.containsKey(name)) {
			solutions.remove(name);
		}
		if (algorithm.compareTo("DFS") == 0) {
			s = new DFS().search(new MazeAdapter(maze));
			controller.displayMessage("DFS for " + name + " is ready\n");
			solutions.put(name, s);
		} else if (algorithm.compareTo("BestFirstSearch") == 0) {
			s = new BestFirstSearch().search(new MazeAdapter(maze));
			controller.displayMessage("BestFirstSearch for " + name + " is ready\n");
			solutions.put(name, s);
		} else if (algorithm.compareTo("BreadthFirstSearch") == 0) {
			s = new BreadthFirstSearch().search(new MazeAdapter(maze));
			controller.displayMessage("BreadthFirstSearch for " + name + " is ready\n");
			solutions.put(name, s);
		}

	}

	public Solution getSolution(String name) {

		return solutions.get(name);

	}

	public int[][] getCrossSection(String axis, int index, String name) {
		Maze3d maze = mazes.get(name);
		switch (axis) {
		case "x":
		case "X":
			return maze.getCrossSectionByX(index);
		case "y":
		case "Y":
			return maze.getCrossSectionByY(index);
		case "z":
		case "Z":
			return maze.getCrossSectionByZ(index);
		default:
			return null;
		}

	}

	public String MazeSize(String name) {
		if (mazes.containsKey(name)){
			Maze3d maze = mazes.get(name);
			int size = maze.toString().length();	
			return Integer.toString(size) + "\n";
		}else
			return "Couldn't find maze!";		
	}


	public String MazeFileSize(String fileName) {
		java.io.File file = new java.io.File(fileName);
		if (file.length() == 0) {
			return "File Not Found!";
		}
		return Long.toString(file.length()) + " Bytes\n";

	}

	public boolean mazeExists(String name) {
		if (mazes.containsKey(name))
			return true;
		return false;
	}
}
