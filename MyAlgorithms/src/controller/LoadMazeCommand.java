package controller;

import model.Model;

public class LoadMazeCommand implements Command {

	private Model model;
	
	public LoadMazeCommand(Model model) {
		this.model = model;
		
	}
	
	@Override
	public void doCommand(String[] args) {
		String fileName = args[0];
		String name = args[1];
		
		System.out.println("Maze name: "+ name + " "+ "fileName: " + fileName);
		
		
		model.loadMaze(fileName, name);

	}

}
