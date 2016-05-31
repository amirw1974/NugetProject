package presenter;

import algorithms.mazeGenerator.Maze3d;
import model.Model;
import view.View;

public class DisplayMazeCommand implements Command {

	private Model model;
	private View view;
	
	public DisplayMazeCommand(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void doCommand(String[] args) {
		String name = args[0];
		Maze3d maze = model.getMaze(name);
		if (maze == null)
			view.displayMessage("Maze " + name + "not found");
		else
			view.displayMaze(maze);

	}

}
