package model;

import algorithms.mazeGenerator.Maze3d;
import algorithms.search.Solution;

public interface Model {
	void generateMaze(String name, int rows, int colums, int levels);
	Maze3d display(String name);
	void saveMaze(String name, String fileName);
	void loadMaze(String fileName, String name);
	Maze3d getMaze(String name);
	public void SolveMaze(String name, String algorithm);
	public String MazeSize (String name);
	public String MazeFileSize (String fileName);
	public Solution getSolution(String name);
	public int [][] getCrossSection (String axis, int index , String name);
	boolean mazeExists(String string);
}
