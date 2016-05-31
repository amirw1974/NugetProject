package presenter;

import model.Model;

public class GenerateMazeCommand implements Command {
	private Model model;
	public GenerateMazeCommand(Model model) {
		this.model = model;
	}	
	
	@Override
	public void doCommand(String[] args) {
		String name = args[0];
		int rows = Integer.parseInt(args[1]);
		int cols = Integer.parseInt(args[2]);
		int levels = Integer.parseInt(args[3]);
		model.generateMaze(name, rows, cols, levels);
	}

}
