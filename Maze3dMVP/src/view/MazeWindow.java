package view;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerator.Position;
import presenter.Command;
import presenter.DirCommand;
import presenter.DisplayCrossSec;
import presenter.DisplayMazeCommand;
import presenter.DisplaySolution;
import presenter.ExitCommand;
import presenter.FileSizeCommand;
import presenter.GenerateMazeCommand;
import presenter.LoadMazeCommand;
import presenter.MazeSizeCommand;
import presenter.SaveMazeCommand;
import presenter.SolveMaze;

public class MazeWindow extends BasicWindow {

	private MazeDisplay mazeDisplay;	
	
	@Override
	protected void initWidgets() {
		shell.setSize(600, 500);	
		
		GridLayout layout = new GridLayout(2, false);
		shell.setLayout(layout);
		
		Composite toolbar = new Composite(shell, SWT.NONE);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		toolbar.setLayout(rowLayout);
		
		Button btnGenerateMaze = new Button(toolbar, SWT.PUSH);
		btnGenerateMaze.setText("Generate");
		Button btnDisplayMaze = new Button(toolbar, SWT.PUSH);
		btnDisplayMaze.setText("Display");
		Button btnSolveMaze = new Button(toolbar, SWT.PUSH);
		btnSolveMaze.setText("Solve");
		Button btnDisplaySolution= new Button(toolbar, SWT.PUSH);
		btnDisplaySolution.setText("Display Solution");
		Button btnGoUp = new Button(toolbar, SWT.PUSH);
		btnGoUp.setText("Up");
		Button btnGoDown = new Button(toolbar, SWT.PUSH);
		btnGoDown.setText("Down");
		
		
		
		
		
		mazeDisplay = new Maze2dDisplay(shell, SWT.BORDER);
		int[][] mazeData={
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,1,1,0,1,0,0,1},
				{0,0,1,1,1,1,1,0,0,1,0,1,0,1,1},
				{1,1,1,0,0,0,1,0,1,1,0,1,0,0,1},
				{1,0,1,0,1,1,1,0,0,0,0,1,1,0,1},
				{1,1,0,0,0,1,0,0,1,1,1,1,0,0,1},
				{1,0,0,1,0,0,1,0,0,0,0,1,0,1,1},
				{1,0,1,1,0,1,1,0,1,1,0,0,0,1,1},
				{1,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,0,1,1},
				
				
			};
		mazeDisplay.setMazeData(mazeData);
		mazeDisplay.setCharacterPosition(new Position(1, 1, 1));
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}

	
}
