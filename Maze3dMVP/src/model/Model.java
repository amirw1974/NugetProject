package model;

import algorithms.mazeGenerator.Maze3d;

public interface Model {
	void generateMaze(String name, int rows, int cols, int levels);
	void saveMaze(String name, String fileName);
	void loadMaze(String fileName, String name);
	Maze3d getMaze(String name);
	String getMessage();
}
