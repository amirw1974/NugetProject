package controller;

import model.Model;
import view.View;

public class MazeSizeCommand implements Command {
	
	private Model m;
	private View v;
	
	public MazeSizeCommand(View v , Model m) {
		this.m = m;
		this.v = v;
	}
	
	@Override
	public void doCommand(String[] args) {
		if (args.length == 1){
			v.PrintOut(m.MazeSize(args[0]));
		}
		else{
			v.PrintOut("maze_size [(String) name]");
		}

	}

	

}
