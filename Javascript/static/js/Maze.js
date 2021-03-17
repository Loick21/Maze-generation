const direction = {
    UP    : "up",
    DOWN  : "down",
    LEFT  : "left",
    RIGHT : "right",
}

class Maze{

    constructor(width,height){

        this.cell_check = [];
        this.stack = [];
        this.width = width;
        this.height = height;
        this.maze = new Array(this.height);
        for(let i = 0; i < this.height; i++){
            this.maze[i] = new Array(this.width);
        }
        this.init_maze();
    }

    init_maze(){
        for(let i = 0; i < this.height; i++){
            let row = [];
            for(let j = 0; j < this.width; j++){
                row[j] = new Cell(i,j);
            }
            this.maze[i] = row;
        }
        this.maze[0][0].set_up_wall(false);
        this.maze[this.height-1][this.width-1].set_down_wall(false);
    }

    find_neigbors(cell){

        let neigbors_dir = [];
        let x = cell.get_x();
        let y = cell.get_y();

        if(cell.get_x() > 0 && !this.cell_check.some(c => (c.get_x() === x-1 && c.get_y() === y))) neigbors_dir.push(direction.UP);
        if(cell.get_x() < this.maze.length-1 && !this.cell_check.some(c => (c.get_x() === x+1 && c.get_y() === y))) neigbors_dir.push(direction.DOWN);
        if(cell.get_y() > 0 && !this.cell_check.some(c => (c.get_x() === x && c.get_y() === y-1))) neigbors_dir.push(direction.LEFT);
        if(cell.get_y() < this.width-1 && !this.cell_check.some(c => (c.get_x() === x && c.get_y() === y+1))) neigbors_dir.push(direction.RIGHT);

        return neigbors_dir;
    }

    maze_generation(){
        let cell_total = (this.width) * (this.height);
        let current_cell = this.maze[0][0];

        this.cell_check.push(current_cell);
        let visited = 1;

        while(visited < cell_total){
            let dir_neigbors = this.find_neigbors(current_cell);

            if(dir_neigbors.length > 0){
                let x = current_cell.get_x();
                let y = current_cell.get_y();

                let rand_number = Math.floor(Math.random()*dir_neigbors.length);
                let dir = dir_neigbors[rand_number];

				if(dir === direction.UP) {
					this.maze[x][y].set_up_wall(false);
					this.maze[x-1][y].set_down_wall(false);
					current_cell = this.maze[x-1][y];
				}
				else if(dir === direction.DOWN) {
					this.maze[x][y].set_down_wall(false);
					this.maze[x+1][y].set_up_wall(false);
					current_cell = this.maze[x + 1][y];

				}
				else if(dir === direction.LEFT) {
					this.maze[x][y].set_left_wall(false);
					this.maze[x][y-1].set_right_wall(false);
					current_cell = this.maze[x][y-1];
				}	
				else if(dir === direction.RIGHT) {
					this.maze[x][y].set_right_wall(false);
					this.maze[x][y+1].set_left_wall(false);
					current_cell = this.maze[x][y+1];
				}
				
				this.cell_check.push(current_cell);
				this.stack.push(current_cell);
				visited++;
			}
			else {
				this.stack.pop();
				current_cell = this.stack[this.stack.length-1];
			}
            
        }
    }


}