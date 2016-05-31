package view;

import java.util.HashMap;

import algorithms.mazeGenerator.Maze3d;
import algorithms.search.Solution;
import controller.Command;

public interface View {
	void displayMessage(String message);
	void start();
	void sendCommands(HashMap<String, Command> commands);
	void displayMaze(Maze3d maze);
	void displaySolution(Solution s);
	public void PrintOut (String str);
	void displayCrossSection(int[][] maze2d);
	
}
