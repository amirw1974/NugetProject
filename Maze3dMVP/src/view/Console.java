package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.custom.SashForm;

public class Console extends MazeWindow{
	protected Shell shell;
	private Text MazeName;
	private Text columns;
	private Text rows;
	private Text levels;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Console window = new Console();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		
		
		shell = new Shell();
		shell.setMinimumSize(new Point(50, 39));
		shell.setSize(603, 421);
		shell.setText("SWT Application");
		shell.setSize(1000, 750);
		GridLayout layout = new GridLayout(1, false);
		shell.setLayout(layout);
		name.setLayoutData(new RowData(97, SWT.DEFAULT));
		x.setLayoutData(new RowData(97, SWT.DEFAULT));
		z.setLayoutData(new RowData(100, SWT.DEFAULT));
		y.setLayoutData(new RowData(97, SWT.DEFAULT));
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
	
//		mazeDisplay = new Maze2dDisplay(shell, SWT.BORDER);
//		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);
		
		MenuItem fileItem = new MenuItem(bar, SWT.CASCADE);
		fileItem.setText("File");
		
		Menu submenu = new Menu(shell, SWT.DROP_DOWN);
		fileItem.setMenu(submenu);
		
		MenuItem saveItem = new MenuItem(submenu, SWT.PUSH);		
		saveItem.setText("Save File \tCtrl+S");
		saveItem.setAccelerator(SWT.CTRL + 'S');

		
		
		//Music on/off check box
		
		MenuItem musicItem = new MenuItem(bar, SWT.CASCADE);
		musicItem.setText("Music");
		
		Menu submenu2 = new Menu(shell, SWT.DROP_DOWN);
		musicItem.setMenu(submenu2);
		
		MenuItem OnOff = new MenuItem(submenu2, SWT.CHECK);
		OnOff.setText("On / Off");
		saveItem.setAccelerator(SWT.CTRL + 'M');
		
		
		
		
		
		MenuItem mntmNewRadiobutton_1 = new MenuItem(submenu, SWT.RADIO);
		mntmNewRadiobutton_1.setText("New RadioButton");
		
		
	}
}
