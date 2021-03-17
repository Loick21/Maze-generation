let maze;

$(window).on('load',function() {
    $('#spinner').addClass("d-none");
});

$(document).ready(function(){
    const params = new URLSearchParams(window.location.search);
    
    let maze_width = params.get('maze-col'); 
    let maze_height= params.get('maze-row'); 

    maze = new Maze(maze_width,maze_height);
    maze.maze_generation();

    for(let i = 0; i < maze.height;i++){
        $("#maze_render_table").append(`<tr id=${i}> </tr>`);

        for(let j = 0; j < maze.width;j++){
            // let id = idgenerator(i,j);
            $(`#${i}`).append(`<td id=${i}-${j}> </td>`);
            
            if(maze.maze[i][j].get_up_wall())$(`#${i}-${j}`).addClass('up_wall');
            // else $(`#${i}${j}`).addClass('no_up_wall');

            if(maze.maze[i][j].get_down_wall())$(`#${i}-${j}`).addClass('down_wall');
            // else $(`#${i}${j}`).addClass('no_down_wall');

            if(maze.maze[i][j].get_left_wall())$(`#${i}-${j}`).addClass('left_wall');
            // else $(`#${i}${j}`).addClass('no_left_wall');

            if(maze.maze[i][j].get_right_wall())$(`#${i}-${j}`).addClass('right_wall');
            // else $(`#${i}${j}`).addClass('no_right_wall');
        }
    }

    function idgenerator(i,j){
        const x = 3;
        const y = 5;
        return Math.abs(x-i) * j + Math.abs(y-j) * i; 
    }

});