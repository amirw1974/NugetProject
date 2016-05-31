package controller;

import algorithms.search.Solution;
import model.Model;
import view.View;

public class DisplaySolution implements Command {
	Model model;
	View view;
	
	public DisplaySolution(Model model, View view) {
		this.model=model;
		this.view=view;
	}
	@Override
	public void doCommand(String[] args) {
		Solution s= model.getSolution(args[0]);
		view.displaySolution(s);
	}

}
