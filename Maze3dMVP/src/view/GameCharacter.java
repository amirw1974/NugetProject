package view;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import algorithms.mazeGenerator.Maze3d;
import algorithms.mazeGenerator.Position;

public class GameCharacter {
	private static final String icon = "images/3.png";
	private Position pos;
	private Position goalPos;
	public Position getPos() {
		return pos;
	}
	public void setPos(Position pos) {
		this.pos = pos;
	}
	public Position getGoalPosition() {
		return goalPos;
	}
	public void setGoalPos(Position pos) {
	this.goalPos = pos;
	}

	public void draw(PaintEvent e, int cellWidth, int cellHeight) {
		e.gc.setBackground(new Color(null, 255, 255, 255));
		
		Image img = new Image(null, icon);
		e.gc.drawImage(img, 0, 0, 128, 128, pos.x * cellWidth, pos.y * cellHeight, cellWidth, cellHeight);
	}
	public void moveLeft() {
		
		
	}
	
	
	
}

