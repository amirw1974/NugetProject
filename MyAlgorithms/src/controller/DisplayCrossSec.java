package controller;

import model.Model;
import view.View;

public class DisplayCrossSec implements Command {
	private Model m;
	private View v;

	public DisplayCrossSec(View v, Model m) {
		this.m = m;
		this.v = v;
	}

	@Override
	public void doCommand(String[] args) {
	
		if (args.length == 3) {
			
			int[][] maze2d = (m).getCrossSection(args[0], Integer.parseInt(args[1]), args[2]);
			(v).PrintOut(args[2] + "'s Maze - Cross by the " + args[0] + " axis \n");
			v.displayCrossSection(maze2d);

		} else {
			(v).PrintOut("display_cross_section [(String) X/Y/Z] [(int) Index] [(String)name]\n");
		}

	}

}
