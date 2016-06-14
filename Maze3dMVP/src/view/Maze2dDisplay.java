package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.Position;

public class Maze2dDisplay extends MazeDisplay {

	public Maze2dDisplay(Composite parent, int style, Maze3d maze) {
		super(parent, style);	
		maze3d = maze;
		mazeData = maze.getMaze();
		
		this.setBackground(new Color(null, 255, 255, 255));
	}

	@Override
	protected void drawMaze(PaintEvent e) {
		e.gc.setBackground(new Color(null, 150, 150, 150));
		
		// width and height of the canvas
		int width = getSize().x;
		int height = getSize().y;
		
		// width and height of each cell
		int cellWidth = width / mazeData[0][0].length;
		int cellHeight = height / mazeData[0].length;
		
		for (int i = 0; i < mazeData.length; i++) {
			for (int j = 0;j < mazeData[0].length; j++) {
				if (mazeData[i][j][character.getPos().z] != 0) {
					e.gc.fillRectangle(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
				}
			}
		}		
		
		character.draw(e, cellWidth, cellHeight);
	}

	protected void goLeft() {
		Position pos = character.getPos();	
		if((pos.getY()-1>=0) && (mazeData[pos.getX()-1][pos.getY()][pos.getZ()] == 0))
		{
			character.setPos(new Position(pos.x - 1, pos.y,pos.z));
			this.redraw();
		}
		
	}

	@Override
	protected void goRight() {
		Position pos = character.getPos();
		if((pos.getX()+1 < maze3d.getColumns()) && (mazeData[pos.getX()][pos.getY()+1][pos.getZ()] == 0))
		{
		character.setPos(new Position(pos.x + 1, pos.y,pos.z));
		this.redraw();
		} else
		{
			System.out.println("cant move");
		}
	}

	@Override
	protected void goUp() {
		Position pos = character.getPos();		
		character.setPos(new Position(pos.x, pos.y-1,pos.z));
		this.redraw();
	}

	@Override
	protected void goDown() {
		Position pos = character.getPos();		
		character.setPos(new Position(pos.x, pos.y+1,pos.z));
		this.redraw();

	}

	@Override
	protected void goFloorDown() {
		Position pos = character.getPos();		
		character.setPos(new Position(pos.x, pos.y,pos.z+1));
		this.redraw();
	}

	@Override
	protected void goFloorUp() {
		Position pos = character.getPos();		
		character.setPos(new Position(pos.x, pos.y,pos.z-1));
		this.redraw();

	}

}
