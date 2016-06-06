package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;

public class Presenter implements Observer {
	private Model model;
	private View view;
	private HashMap<String, Command> viewCommands = new HashMap<String,Command>();
	private HashMap<String, Command> modelCommands = new HashMap<String,Command>();
	
	public Presenter(Model model, View view) {
		this.model = model;
		this.view = view;
		createCommands();
	}
	
	private void createCommands() {
		viewCommands = new HashMap<String, Command>();
		viewCommands.put("generate_maze_3d", new GenerateMazeCommand(model));
		viewCommands.put("save_maze", new SaveMazeCommand(model));
		viewCommands.put("display", new DisplayMazeCommand(model, view));		
		viewCommands.put("dir", new DirCommand());		
		viewCommands.put("load_maze", new LoadMazeCommand(model));
		viewCommands.put("maze_size", new MazeSizeCommand(view, model));
		viewCommands.put("file_size", new FileSizeCommand(view,model));	
		viewCommands.put("solve", new SolveMaze(model));
		viewCommands.put("display_solution", new DisplaySolution(model,view));
		viewCommands.put("display_cross_section", new DisplayCrossSec(view, model));
		viewCommands.put("exit", new ExitCommand(view));
		
		
		modelCommands = new HashMap<String, Command>();
		modelCommands.put("display_message", new DisplayMessageCommand(model, view));
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == model) {
			String commandName = (String)arg;
			Command command = modelCommands.get(commandName);
			command.doCommand(null);
		}
		else if (o == view) {
			String commandLine = (String)arg;
			String[] arr = commandLine.split(" ");
			String commandName = arr[0];	
			
			String[] args = null;
			if (arr.length > 1) {
				args = new String[arr.length - 1];			
				System.arraycopy(arr, 1, args, 0, arr.length - 1);
			}
			Command command = viewCommands.get(commandName);
			command.doCommand(args);
			
		}
	}

	
	
	
	
}
