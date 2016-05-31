package view;

import algorithms.mazeGenerator.Maze3d;
import java.util.HashMap;

public interface View {
	void displayMessage(String message);
	void displayMaze(Maze3d maze);
	void start();	
}
