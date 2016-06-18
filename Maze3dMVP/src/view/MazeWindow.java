package view;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ListSelectionModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.Position;
import algorithms.search.Action;
import algorithms.search.Solution;
import algorithms.search.State;
import io.MyCompressorOutputStream;
import presenter.Command;
public class MazeWindow extends BasicWindow {
	private MazeDisplay mazeDisplay;
	Maze3d maze;
	Text name;
	Text x;
	Text y;
	Text z;
	String outString = "";
	String[] selectedItems;
	int AreThereAnyMazes = 0;
	Image image = new Image(display, "images/game.png");
	Clip music;
	Clip sound;
	@Override
	protected void initWidgets() {
		shell.setSize(1000, 750);
		GridLayout layout = new GridLayout(2, false);
		shell.setLayout(layout);
		Composite toolbar = new Composite(shell, SWT.FILL);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		toolbar.setLayout(rowLayout);
		toolbar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		// For maze testing
		name = new Text(toolbar, SWT.BORDER);
		x = new Text(toolbar, SWT.BORDER);
		y = new Text(toolbar, SWT.BORDER);
		z = new Text(toolbar, SWT.BORDER);
		name.setText("shlomi");
		x.setText("20");
		y.setText("20");
		z.setText("1");
		// playMusic(new File("music/sound.wav"));
		shell.setText("Game of Thrones");
		shell.setImage(image);
		//////////////////////////////////////////////////////////////////////////////
		////////////////////////////// MakingGUI//////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////
		Button btnGenerateMaze = new Button(toolbar, SWT.PUSH);
		btnGenerateMaze.setText("GenerateMaze");
		Label lblDisplayMazes = new Label(toolbar, SWT.NONE);
		lblDisplayMazes.setText("DisplayMaze");
		List listDisplayMaze = new List(toolbar, SWT.VERTICAL | SWT.BORDER);
		Button btnDisplayMaze = new Button(toolbar, SWT.PUSH);
		btnDisplayMaze.setText("DisplayMaze");
		Button btnMute = new Button(toolbar, SWT.PUSH);
		btnMute.setText("Mute");
		Button btnMusic = new Button(toolbar, SWT.PUSH);
		btnMusic.setText("Play Music");
		mazeDisplay = new Maze2dDisplay(shell, SWT.BORDER);
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);
		MenuItem fileItem = new MenuItem(bar, SWT.CASCADE);
		fileItem.setText("File");
		Menu submenu = new Menu(shell, SWT.DROP_DOWN);
		fileItem.setMenu(submenu);
		MenuItem saveItem = new MenuItem(submenu, SWT.PUSH);
		MenuItem loadItem = new MenuItem(submenu, SWT.PUSH);
		saveItem.setText("Save File \tCtrl+S");
		saveItem.setAccelerator(SWT.CTRL + 'S');
		loadItem.setText("Load File \tCtrl+Z");
		loadItem.setAccelerator(SWT.CTRL + 'Z');
		////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////
		///////////////////////////// Listeners////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////
		listDisplayMaze.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				selectedItems = listDisplayMaze.getSelection();
				outString = selectedItems[0];
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		btnGenerateMaze.addListener(SWT.MouseDown, new Listener() {
			@Override
			// Checks if the maze exist or not aswell.
			public void handleEvent(Event arg0) {
				int temp = 0;
				if (AreThereAnyMazes == 0) {
					setChanged();
					notifyObservers("generate_maze_3d" + " " + name.getText() + " " + x.getText() + " " + y.getText() + " " + z.getText());
					listDisplayMaze.add(name.getText());
					AreThereAnyMazes = 1;
					return;
				} else if (AreThereAnyMazes == 1) {
					temp = listDisplayMaze.getItemCount();
					selectedItems = listDisplayMaze.getItems();
					outString = selectedItems[0];
					for (int i = 0; i < temp; i++) {
						if ((selectedItems[i].equals(name.getText()))) {
							displayMessage("Maze already exist");
							return;
						}
					}
					// if (outString != name.getText()) {
					setChanged();
					notifyObservers("generate_maze_3d" + " " + name.getText() + " " + x.getText() + " " + y.getText() + " " + z.getText());
					listDisplayMaze.add(name.getText());
					// }
				}
			}
		});
		btnDisplayMaze.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				String[] getlists = listDisplayMaze.getSelection();
				if ((getlists.length) == 0) {
					displayMessage("Please select a maze");
					return;
				}
				setChanged();
				notifyObservers("display" + " " + outString);
			}
		});
		btnMusic.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				music.start();
			}
		});
		btnMute.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				music.stop();
			}
		});
	

	/////////////////////////////////////////////////
	////////////////////////////// EndofGUI///////////
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	}
	@Override
	public void displayMessage(String message) {
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				MessageBox msg = new MessageBox(shell);
				msg.setMessage(message);
				msg.open();
			}
		});
	}
	@Override
	public void displayMaze(Maze3d maze) {
		mazeDisplay.setCharacterPosition(maze.getStartPosition());
		mazeDisplay.character.setGoalPos(maze.getGoalPosition());
		// MazeWindow.mazeDisplay.character.setPos(maze.getStartPosition());
		int[][][] mazeData = maze.getMaze();
		mazeDisplay.setMazeData(mazeData);
		mazeDisplay.redraw();
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
	private void playMusic(File file) {
		try {
			music = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			music.open(inputStream);
			// loop infinitely
			music.setLoopPoints(0, -1);
			music.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
