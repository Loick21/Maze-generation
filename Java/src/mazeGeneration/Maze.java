package mazeGeneration;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class Maze {
	
	public Cell maze [][];
	private int W = 20, H = 20;
	private List<Cell> cell_check = new ArrayList<>();
	private Deque<Cell> stack = new ArrayDeque<>();
	private enum direction {
		UP,DOWN,LEFT,RIGHT
	}
	
	public Maze() {
		init_maze();
		cell_check.add(new Cell(3,4));
	}
	
	public void init_maze() {
		maze = new Cell [W][H];
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[i].length; j++) {
				maze[i][j] = new Cell(i,j);
			}
		}
		maze[0][0].setLeft_wall(false);
		maze[maze.length-1][maze[0].length-1].setRight_wall(false);
	}
	
	public void show_maze() {
		for(int i =0; i < maze.length;i++) {
			for(int j=0; j < maze[i].length; j++) {
				System.out.print(maze[i][j].toString());
			}
			System.out.println();
		}
	}
	
	public void maze_generation() {
		
		int cell_total = maze.length * maze[0].length;
		Cell current_cell = maze[0][0];
		cell_check.add(current_cell);
		int visited = 1;
		
		while(visited < cell_total) {
			List<direction> dir_neigbors = find_neigbors(current_cell);
			
			System.out.println(dir_neigbors);
			
			show_maze();
			
			
			if(!dir_neigbors.isEmpty()) {
				Random random = new Random();
				direction dir = dir_neigbors.get(random.nextInt(dir_neigbors.size()));
				System.out.println(dir_neigbors);
				System.out.println("Random direction " + dir);
				if(dir == direction.UP) {
					maze[current_cell.getX()][current_cell.getY()].setUp_wall(false);
					maze[current_cell.getX()-1][current_cell.getY()].setDown_wall(false);
					current_cell = maze[current_cell.getX()-1][current_cell.getY()];
					System.out.println("current cell x = " + current_cell.getX() +  " y = " + current_cell.getY() );
				}
				else if(dir == direction.DOWN) {
					maze[current_cell.getX()][current_cell.getY()].setDown_wall(false);
					maze[current_cell.getX()+1][current_cell.getY()].setUp_wall(false);
					int x = current_cell.getX() + 1;
					current_cell = maze[current_cell.getX() + 1][current_cell.getY()];
					System.out.println("current cell x = " + current_cell.getX() +  " y = " + current_cell.getY() );
					System.out.println("Diertion down " + x);

				}
				else if(dir == direction.LEFT) {
					maze[current_cell.getX()][current_cell.getY()].setLeft_wall(false);
					maze[current_cell.getX()][current_cell.getY()-1].setRight_wall(false);
					current_cell = maze[current_cell.getX()][current_cell.getY()-1];
					System.out.println("current cell x = " + current_cell.getX() +  " y = " + current_cell.getY() );
				}	
				else if(dir == direction.RIGHT) {
					maze[current_cell.getX()][current_cell.getY()].setRight_wall(false);
					maze[current_cell.getX()][current_cell.getY()+1].setLeft_wall(false);
					current_cell = maze[current_cell.getX()][current_cell.getY()+1];
					System.out.println("current cell x = " + current_cell.getX() +  " y = " + current_cell.getY() );
				}
				
				cell_check.add(current_cell);
				stack.push(current_cell);
				visited++;
			}
			else {
				stack.pop();
				System.out.println(visited);
				current_cell = stack.getFirst();
			}
		}
	}
	
	public List<direction> find_neigbors(Cell cell) {
		
		List<direction> neigbors_dir = new ArrayList<>();
		System.out.println(!cell_check.contains(cell));
		
		if(cell.getX() > 0 && !cell_check.contains(maze[cell.getX()-1][cell.getY()])) neigbors_dir.add(direction.UP);
	
		if(cell.getX() < maze.length-1 && !cell_check.contains(maze[cell.getX()+1][cell.getY()]) ) neigbors_dir.add(direction.DOWN);
		
		if(cell.getY() > 0 && !cell_check.contains(maze[cell.getX()][cell.getY()-1])) neigbors_dir.add(direction.LEFT);
		
		if(cell.getY() < maze[0].length-1 && !cell_check.contains(maze[cell.getX()][cell.getY()+1])) neigbors_dir.add(direction.RIGHT);

		return neigbors_dir;
	}
	
	
}
