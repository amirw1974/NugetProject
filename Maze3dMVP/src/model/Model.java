package model;

import algorithms.mazeGenerator.Maze3d;
import algorithms.search.Solution;

public interface Model {
	void generateMaze(String name, int rows, int cols, int levels);
	void saveMaze(String name, String fileName);
	void loadMaze(String fileName, String name);
	Maze3d getMaze(String name);
	String getMessage();
	public int[][] getCrossSection(String axis, int index, String name);
	Solution getSolution(String name);
	String MazeFileSize(String fileName);
	String MazeSize(String name);
	void SolveMaze(String name, String algorithm);

	
}
