package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.Position;

public abstract class MazeDisplay extends Canvas {
	private Maze3d maze3d;

	protected GameCharacter character = new GameCharacter();

	protected abstract void drawMaze(PaintEvent e);

	protected abstract void goLeft();

	protected abstract void goRight();

	protected abstract void goUp();

	protected abstract void goDown();

	protected abstract void goFloorUp();

	protected abstract void goFloorDown();

	protected int[][] mazeData;

	public void setMazeData(int[][] mazeData) {
		this.mazeData = mazeData;
	}

	public void setCharacterPosition(Position pos) {
		character.setPos(pos);
	}

	public MazeDisplay(Composite parent, int style) {
		super(parent, style);
		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				drawMaze(e);
			}
		});

		this.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				Position charPos = character.getPos();

				switch (e.keyCode) {
				case SWT.ARROW_LEFT:
					if (mazeData[charPos.x - 1][charPos.y] == 1)
						return;
					else
						goLeft();
				case SWT.ARROW_RIGHT:
					if (mazeData[charPos.x + 1][charPos.y] == 1)
						return;
					else
						goRight();
				case SWT.ARROW_UP:
					if (mazeData[charPos.x][charPos.y - 1] == 1)
						return;
					else
						goUp();
				case SWT.ARROW_DOWN:
					if (mazeData[charPos.x][charPos.y + 1] == 1)
						return;
					else
						goDown();

				case SWT.SHIFT:
					goFloorUp();
					break;
				case SWT.CONTROL:
					goFloorDown();
					break;

				}
			}

		});

	}
}
