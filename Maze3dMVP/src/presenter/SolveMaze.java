package presenter;

import model.Model;

public class SolveMaze implements Command {
	Model model;
	
	public SolveMaze(Model model) {
	 this.model= model;
	}

	@Override
	public void doCommand(String[] args) {
		model.SolveMaze(args[0], args[1]);

	}

}
