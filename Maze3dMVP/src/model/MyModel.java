package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import algorithms.demo.MazeAdapter;
import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.MyMaze3dGenerator;
import algorithms.mazeGenerator.Position;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.CommonSearcher;
import algorithms.search.DFS;
import algorithms.search.Solution;
import boot.Settings;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class MyModel extends Observable implements Model {
	private ExecutorService					pool;
	Settings								settings;
	private HashMap<String, Maze3d>			mazes		= new HashMap<String, Maze3d>();
	private HashMap<String, Solution>		solutions	= new HashMap<>();
	private String							message;
	/////////////////////////////////////////////////////
	Position								pos;
	private HashMap<String, CommonSearcher>	algorithms	= new HashMap<String, CommonSearcher>();
	public String getMessage() {
		return message;
	}
	public MyModel(Settings settings) {
		pool = Executors.newFixedThreadPool(settings.getNumOfMaxThread());
		this.settings = settings;
	}
	@Override
	public void generateMaze(String name, int rows, int cols, int levels) {
		Callable<Maze3d> c = new Callable<Maze3d>(){
			@Override
			public Maze3d call() throws Exception {
				MyMaze3dGenerator mg = new MyMaze3dGenerator();
				Maze3d maze = mg.generate(rows,cols,levels);
				mazes.put(name,maze);
				message = "Maze " + name + " is ready\n";
				setChanged();
				notifyObservers("display_message");
				return maze;
			}
		};
		Future<Maze3d> futureMaze = pool.submit(c);
		// submit- take out from poolThread even if the maze is not ready yet!
	}
	public Maze3d getMaze(String name) {
		return mazes.get(name);
	}
	@Override
	public void saveMaze(String name, String fileName) {
		if(!mazes.containsKey(name)){
			// controller.displayMessage("Maze " + name + " does not exist\n");
			message = "Maze " + name + " does not exist\n";
			setChanged();
			notifyObservers("display_message");
			return;
		}
		Maze3d maze = mazes.get(name);
		try{
			OutputStream out = new MyCompressorOutputStream(new FileOutputStream(fileName));
			byte[] bytes = maze.toByteArray();
			out.write(bytes.length);
			out.write(bytes);
			out.flush();
			out.close();
		} catch(FileNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void loadMaze(String fileName, String name) {
		if(mazes.containsKey(name)){
			message = "Maze " + name + " exist\n";
			setChanged();
			notifyObservers("display_message");
			return;
		}
		try{
			InputStream in = new MyDecompressorInputStream(new FileInputStream(new File(fileName)));
			byte b[] = new byte[in.read()];
			in.read(b);
			mazes.put(name,new Maze3d(b));
			in.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public int[][] getCrossSection(String axis, int index, String name) {
		Maze3d maze = mazes.get(name);
		switch (axis){
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
	@Override
	public Solution getSolution(String name) {
		return solutions.get(name);
	}
	@Override
	public String MazeFileSize(String fileName) {
		java.io.File file = new java.io.File(fileName);
		if(file.length() == 0){
			return "File Not Found!";
		}
		return Long.toString(file.length()) + " Bytes\n";
	}
	@Override
	public String MazeSize(String name) {
		if(mazes.containsKey(name)){
			Maze3d maze = mazes.get(name);
			int size = maze.toString().length();
			return Integer.toString(size) + "\n";
		} else
			return "Couldn't find maze!";
	}
	@Override
	public void SolveMaze(String name, String algorithm) {
		Callable<Solution> c = new Callable<Solution>(){
			@Override
			public Solution call() throws Exception {
				settings.setSolvingAlgorithm(algorithm);
				Maze3d maze;
				maze = mazes.get(name);
				// System.out.println(maze);
				Solution s = null;
				// TODO: Add if
				if(solutions.containsKey(name)){
					solutions.remove(name);
				}
				if(settings.getSolvingAlgorithm().equals("DFS")){
					s = new DFS().search(new MazeAdapter(maze));
					message = "DFS for " + name + " is ready\n";
					setChanged();
					notifyObservers("display_message");
					solutions.put(name,s);
				} else if(settings.getSolvingAlgorithm().equals("BestFirstSearch")){
					s = new BestFirstSearch().search(new MazeAdapter(maze));
					message = "BestFirstSearch for " + name + " is ready\n";
					setChanged();
					notifyObservers("display_message");
					solutions.put(name,s);
				} else if(settings.getSolvingAlgorithm().equals("BreadthFirstSearch")){
					s = new BreadthFirstSearch().search(new MazeAdapter(maze));
					message = "BreadthFirstSearch for " + name + " is ready\n";
					notifyObservers("display_message");
					solutions.put(name,s);
				}
				System.out.println("mymodel solution: " + s);
				return s;
			}
		};
		pool.submit(c);
	}
}
