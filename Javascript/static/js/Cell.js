class  Cell {

    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.up_wall = true;
        this.down_wall = true;
        this.left_wall = true;
        this.right_wall = true;
    }

    get_x(){
        return this.x;
    }

    get_y(){
        return this.y;
    }

    set_up_wall(up_wall){
        this.up_wall = up_wall;
    }

    set_down_wall(down_wall){
        this.down_wall = down_wall;
    }

    set_left_wall(left_wall){
        this.left_wall = left_wall;
    }

    set_right_wall(right_wall){
        this.right_wall = right_wall;
    }

    get_up_wall(){
        return this.up_wall;
    }
    get_down_wall(){
        return this.down_wall;
    }
    get_left_wall(){
        return  this.left_wall ;
    }
    get_right_wall(){
        return this.right_wall;
    }

}