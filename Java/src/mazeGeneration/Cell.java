package mazeGeneration;

import java.util.ArrayList;
import java.util.List;

public class Cell {
	
	private int x,y;
	private boolean up_wall, down_wall, left_wall, right_wall;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		this.up_wall    = true;
		this.left_wall  = true;
		this.down_wall  = true;
		this.right_wall = true;
	}

	
	@Override
	public String toString() {
		String up = up_wall ? "_" :" ";
		String left = left_wall ? "|" :" ";
		String down = down_wall ? "_" :" ";
		String right = right_wall ? "|" :" ";
		return left + up + right + down;
	}
	
	@Override
	public boolean equals(Object obj) {	
		Cell cell = (Cell)obj;
		
		return (this.x == cell.getX() && this.y == cell.getY());
	}

//	public List<Cell> getNeigbors(int width, int heigth){
//		
//		List<Cell> neigbors = new ArrayList<>();
//		
//		if(this.x > 0) neigbors.add(new Cell(this.x - 1, this.y));
//		if(this.x < heigth ) neigbors.add(new Cell(this.x + 1, this.y));
//		if(this.y > 0) neigbors.add(new Cell(this.x, this.y-1));
//		if(this.y < width) neigbors.add(new Cell(this.x , this.y + 1));
//
//		return neigbors;
//	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isUp_wall() {
		return up_wall;
	}

	public void setUp_wall(boolean up_wall) {
		this.up_wall = up_wall;
	}

	public boolean isDown_wall() {
		return down_wall;
	}

	public void setDown_wall(boolean down_wall) {
		this.down_wall = down_wall;
	}

	public boolean isLeft_wall() {
		return left_wall;
	}

	public void setLeft_wall(boolean left_wall) {
		this.left_wall = left_wall;
	}

	public boolean isRight_wall() {
		return right_wall;
	}

	public void setRight_wall(boolean rigth_wall) {
		this.right_wall = rigth_wall;
	}
	
}
