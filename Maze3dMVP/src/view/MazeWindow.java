package view;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
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
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import algorithms.mazeGenerator.Maze3d;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
public class MazeWindow extends BasicWindow {
	private HashMap<String, Maze3d> mazes = new HashMap<String, Maze3d>();
	//
	private MazeDisplay mazeDisplay;
	public Maze2dDisplay maze2dDisplay;
	GameCharacter character = new GameCharacter();
	Maze3d maze;
	Text name;
	Text x;
	Text y;
	int LoadedMaze = 0;
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
		////// MAZE NAME + X + Y + Z///////
		Composite composite_1 = new Composite(toolbar, SWT.NONE);
		composite_1.setLayout(new GridLayout(6, false));
		composite_1.setLayoutData(new RowData(150, 61));
		name = new Text(composite_1, SWT.BORDER);
		GridData gd_MazeName = new GridData(SWT.FILL, SWT.CENTER, true, false, 6, 1);
		gd_MazeName.widthHint = 50;
		name.setLayoutData(gd_MazeName);
		Label lblX = new Label(composite_1, SWT.NONE);
		lblX.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblX.setText("x");
		x = new Text(composite_1, SWT.BORDER);
		GridData gd_columns = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_columns.widthHint = 50;
		x.setLayoutData(gd_columns);
		Label lblY = new Label(composite_1, SWT.NONE);
		lblY.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblY.setText("y");
		y = new Text(composite_1, SWT.BORDER);
		GridData gd_rows = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_rows.widthHint = 50;
		y.setLayoutData(gd_rows);
		Label lblZ = new Label(composite_1, SWT.NONE);
		lblZ.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblZ.setText("z");
		z = new Text(composite_1, SWT.BORDER);
		GridData gd_levels = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_levels.widthHint = 50;
		z.setLayoutData(gd_levels);
		////////////////////////////// FOR TEST
		////////////////////////////// PURPOSES//////////////////////////////
		name.setText("Mazename");
		x.setText("20");
		y.setText("20");
		z.setText("1");
		/////////////////////////////////////////////////////////////////////////////
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
		mazeDisplay = new Maze2dDisplay(shell, SWT.BORDER);
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		// Creating a bar menu
		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);
		// Making a File button in bar
		MenuItem fileItem = new MenuItem(bar, SWT.CASCADE);
		fileItem.setText("File");
		// Making a drop down menu with save/load options
		Menu submenu = new Menu(shell, SWT.DROP_DOWN);
		fileItem.setMenu(submenu);
		MenuItem saveItem = new MenuItem(submenu, SWT.PUSH);
		MenuItem loadItem = new MenuItem(submenu, SWT.PUSH);
		saveItem.setText("Save File \tCtrl+S");
		saveItem.setAccelerator(SWT.CTRL + 'S');
		loadItem.setText("Load File \tCtrl+Z");
		loadItem.setAccelerator(SWT.CTRL + 'Z');
		// Making a Music button in bar
		MenuItem musicItem = new MenuItem(bar, SWT.CASCADE);
		musicItem.setText("Music");
		// Making a drop down menu with a checkbox to enable/disable music
		Menu submenu2 = new Menu(shell, SWT.DROP_DOWN);
		musicItem.setMenu(submenu2);
		MenuItem OnOff = new MenuItem(submenu2, SWT.CHECK);
		OnOff.setText("On/Off \tCtrl+M");
		OnOff.setSelection(true);
		OnOff.setAccelerator(SWT.CTRL + 'M');
		Button btnSolveDFS = new Button(toolbar, SWT.PUSH);
		btnSolveDFS.setText("Solve by DFS");
		Button btnDisplaySolve = new Button(toolbar, SWT.PUSH);
		btnDisplaySolve.setText("Display Solve");
		playMusic(new File("music/sound.wav"));
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
				if (!(x.getText().equals(y.getText()))) {
					displayMessage("X and Y must be equal");
					return;
				}
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
				if (LoadedMaze == 1) {
					Maze3d maze = getMaze(outString);
					displayMaze(maze); // returns name instead of name
					LoadedMaze = 0;
					return;
				}
				setChanged();
				notifyObservers("display" + " " + outString);
			}
		});
		OnOff.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				if (OnOff.getSelection()) {
					music.start();
				} else
					music.stop();
			}
		});
		saveItem.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				OutputStream out;
				FileDialog fd = new FileDialog(shell, SWT.SAVE);
				fd.setText("Save");
				fd.setFilterPath("C:/");
				String[] filterExt = {"*.maz"};
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();
				// System.out.println(maze.toByteArray());
				if (selected != null) {
					try {
						Maze3d currmazed = mazeDisplay.getcurrMaze();
						out = new MyCompressorOutputStream(new FileOutputStream(new File(selected)));
						out.write(currmazed.toByteArray());
						System.out.println(currmazed.toByteArray().length);
						out.flush();
						out.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		loadItem.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event args) {
				InputStream in;
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
				fd.setText("Open");
				fd.setFilterPath("C:/");
				String[] filterExt = {"*.maz"};
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();
				if (selected != null) {
					try {
						in = new MyDecompressorInputStream(new FileInputStream(selected));
						File file = new File(selected);
						byte b[] = new byte[(int) ((file.length()) - 9)];
						in.read(b);
						Maze3d loaded = new Maze3d(b);
						mazeDisplay.setcurrMaze(loaded);
						// get maze name without the full path
						int start = fd.getFilterPath().length();
						int end = selected.length();
						String loadedmazename = selected.substring(start + 1, end - 4);
						mazes.put(loadedmazename, loaded);
						listDisplayMaze.add(loadedmazename);
						in.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					LoadedMaze = 1;
				}
			}
		});
		////////////////////////// try solve/////////////////////////
		btnSolveDFS.addListener(SWT.MouseDown, new Listener() {
			@Override
			// Checks if the maze exist or not aswell.
			public void handleEvent(Event arg0) {
				setChanged();
				notifyObservers("solve" + " " + name.getText() + " " + "DFS");
				// System.out.println(s);
				displayMessage("the sulotion is ready");
				int temp = 0;
				if (AreThereAnyMazes == 1) {
					AreThereAnyMazes = 1;
					return;
				} else if (AreThereAnyMazes == 1) {
					temp = listDisplayMaze.getItemCount();
					selectedItems = listDisplayMaze.getItems();
					outString = selectedItems[0];
					for (int i = 0; i < temp; i++) {
						if ((selectedItems[i].equals(name.getText()))) {
							displayMessage("Mazes solve is ready");
							return;
						}
					}
					// if (outString != name.getText()) {
					setChanged();
					notifyObservers("solve" + " " + name.getText() + " " + "DFS");
					System.out.println("enter");
					// }
				}
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
	public Maze3d getMaze(String name) {
		return mazes.get(name);
	}
}
