package view;

import algorithms.mazeGenerator.Maze3d;
import algorithms.search.Solution;

import java.util.HashMap;

public interface View {
	void displayMessage(String message);
	void displayMaze(Maze3d maze);
	void start();	
	public void displayCrossSection(int[][] maze2d);
	void displaySolution(Solution s);
}
