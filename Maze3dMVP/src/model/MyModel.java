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
import java.util.Observable;

import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.MyMaze3dGenerator;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class MyModel extends Observable implements Model {
	
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	private HashMap<String, Maze3d> mazes = new HashMap<String, Maze3d>();
	private String message;
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public void generateMaze(String name, int rows, int cols, int levels) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {				
				MyMaze3dGenerator mg = new MyMaze3dGenerator();
				Maze3d maze = mg.generate(rows, cols, levels);
				mazes.put(name, maze);
				
				message = "Maze " + name + " is ready\n";
				setChanged();
				notifyObservers("display_message");
				
				//controller.displayMessage("Maze " + name + " is ready\n");				
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
			//controller.displayMessage("Maze " + name + " does not exist\n");
			message = "Maze " + name + " does not exist\n";
			setChanged();
			notifyObservers("display_message");
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
		if (!mazes.containsKey(name)) {
			message = "Maze " + name + " does not exist\n";
			setChanged();
			notifyObservers("display_message");
			return;
		}
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

}
