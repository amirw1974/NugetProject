package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerator.Direction;
import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.Position;

public class Maze2dDisplay extends MazeDisplay {

	public Maze2dDisplay(Composite parent, int style) {
		super(parent, style);
		this.setBackground(new Color(null, 255, 255, 255));

	}

	@Override
	protected void drawMaze(PaintEvent e) {
		if (mazeData == null) {
			return;
		}
		e.gc.setBackground(new Color(null, 150, 150, 150));

		int width = getSize().x;
		int height = getSize().y;
		int cellWidth = width / mazeData.getMaze()[0].length;
		int cellHeight = height / mazeData.getMaze().length;

		// [rows][columns][levels]
		for (int i = 0; i < mazeData.getMaze().length; i++) {
			for (int j = 0; j < mazeData.getMaze()[i].length; j++) {
				int x = j * cellWidth;
				int y = i * cellHeight;
				if (mazeData.getMaze()[i][j][0] != 0) {
					e.gc.fillRectangle(x, y, cellWidth, cellHeight);
				}
			}
		}
		character.draw(e, cellWidth, cellHeight);
	}

	protected void goLeft() {

		Position pos = character.getPos();
		if ((pos.getX() - 1 >= 0) && (mazeData.getMaze()[(pos.x) - 1][(pos.y)][(pos.z)] == 0)) {
			character.setPos(new Position(pos.x - 1, pos.y, pos.z));
			this.redraw();
		}
	}

	@Override
	protected void goRight() {
		Position pos = character.getPos();
		if ((pos.getX() + 1 < mazeData.getMaze()[0].length)
				&& (mazeData.getMaze()[(pos.x) + 1][(pos.y)][(pos.z)] == 0)) {
			character.setPos(new Position(pos.x + 1, pos.y, pos.z));
			this.redraw();
		}
	}

	@Override
	protected void goUp() {
		Position pos = character.getPos();
		if ((pos.getY() - 1 >= 0) && (mazeData.getMaze()[(pos.x)][(pos.y) - 1][(pos.z)] == 0)) {
			character.setPos(new Position(pos.x, pos.y - 1, pos.z));
			this.redraw();
		}
	}

	@Override
	protected void goDown() {
		Position pos = character.getPos();
		if ((pos.y + 1 < mazeData.getMaze().length)
				&& (mazeData.getMaze()[character.getPos().getX()][character.getPos().getY() + 1][character.getPos()
						.getZ()] == 0)) {
			character.setPos(new Position(pos.x, pos.y + 1, pos.z));
			this.redraw();
		}
	}

	@Override
	protected void goFloorDown() {
	}

	@Override
	protected void goFloorUp() {
	}

	@Override
	protected void debugStatus() {
		Position pos = character.getPos();
		System.out.println("Current Position[pos.get()]: {" + pos.getX() + "," + pos.getY() + "," + pos.getZ() + "}");
		System.out.println("Current Position[pos.get()]: {" + (pos.getX() + 1) + "," + (pos.getY() + 1) + ","
				+ (pos.getZ() + 1) + "}");
		System.out.println("Maze legnth: " + mazeData.getMaze().length);
		System.out.println(character.getPos().getY() + 1);
		System.out.println(mazeData.getPossibleDirections(pos));
		

	}

}
