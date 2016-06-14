package view;

import java.awt.MenuItem;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.MyMaze3dGenerator;
import algorithms.mazeGenerator.Position;
import algorithms.search.Solution;
import boot.Run;

public class MazeWindow extends BasicWindow {

	private MazeDisplay mazeDisplay;
	Maze3d maze;

	Text name;
	Text x;
	Text y;
	Text z;

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

		btnGenerateMaze.addListener(SWT.MouseDown, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				setChanged();
				notifyObservers("generate_maze_3d " + name.getText() + " "+ x.getText() + " "+ y.getText() + " " + z.getText());
				System.out.println("generate_maze_3d " + name.getText() + " "+ x.getText() + " "+ y.getText() + " " + z.getText());
				notifyObservers("display" + name.getText());
				System.out.println("display " + name.getText());
	
			}
		});

		Button btnDisplayMaze = new Button(toolbar, SWT.PUSH);
		btnDisplayMaze.setText("Display");
		Button btnSolveMaze = new Button(toolbar, SWT.PUSH);
		btnSolveMaze.setText("Solve");
		btnSolveMaze.addListener(SWT.MouseDown, new Listener() {

			@Override
			public void handleEvent(Event arg0) {

			}
		});

		Button btnDisplaySolution = new Button(toolbar, SWT.PUSH);
		btnDisplaySolution.setText("Display Solution");
		
		name = new Text(toolbar, SWT.BORDER);
		x = new Text(toolbar, SWT.BORDER);
		y = new Text(toolbar, SWT.BORDER);
		z = new Text(toolbar, SWT.BORDER);




	}

	@Override
	public void displayMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMaze(Maze3d maze) {
		System.out.println(maze);
		mazeDisplay = new Maze2dDisplay(shell, SWT.BORDER, maze);

		mazeDisplay.setCharacterPosition(maze.getStartPosition());
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCrossSection(int[][] maze2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(Solution s) {
		// TODO Auto-generated method stub
		
	}

}
