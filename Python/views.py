from django.shortcuts import render
from django.http import HttpResponse
from django.utils import simplejson
from enum import Enum
import random

def homePage(request):

    maze = Maze(10,10)
    maze.generate_maze()
    maze_json = simplejson.dumps(maze.maze)
    return render(request, 'mazeGeneration/index.html',{'maze':maze_json,'height':range(10),'width':range(10)})

class Maze:
    
    def __init__(self,width,height):
        self.width = width 
        self.height = height 
        self.stack = []
        self.cell_check = []
        self.maze = []
        self.init_grid()
        # self.show_maze()

    def init_grid(self):

        for i in range(self.height):
            row = []
            for j in range(self.width):
                row.append(Cell(i,j))
            self.maze.append(row)

        self.maze[0][0].set_left_wall(False)
        self.maze[self.height-1][self.width-1].set_down_wall(False)

    def show_maze(self):
        for row in self.maze:
            for cell in row:
                print(cell.get_x(),cell.get_y(),end='')
            print()
    
    def find_neigbors(self, cell):
        neigbors_dir = []
        x = cell.get_x()
        y = cell.get_y()

        if((x > 0) and  (self.cell_check.__contains__((x-1,y)) == False)):
            neigbors_dir.append(direction.UP)
        if((y < len(self.maze) - 1) and (self.cell_check.__contains__((x+1,y)) == False)):
            neigbors_dir.append(direction.DOWN)
        if((x > 0) and  (self.cell_check.__contains__((x,y-1)) == False)):
            neigbors_dir.append(direction.LEFT)
        if((y < len(self.maze[0]) - 1) and  (self.cell_check.__contains__((x,y+1)) == False)):
            neigbors_dir.append(direction.RIGHT)

        return neigbors_dir


    def generate_maze(self):

        cell_total = len(self.maze) * len(self.maze[0])
        current_cell = self.maze[0][0]
        self.cell_check.append((current_cell.get_x(),current_cell.get_y()))
        visited = 1

        while(visited < cell_total):
            x = current_cell.get_x()
            y = current_cell.get_y()

            dir_neigbors = self.find_neigbors(current_cell)
            print(dir_neigbors) 
            self.show_maze()

            if(dir_neigbors):
                dir = random.choice(dir_neigbors)
                print("direction choose", dir)

                if(dir == direction.UP):
                    self.maze[x][y].set_up_wall(False)
                    self.maze[x-1][y].set_down_wall(False)
                    current_cell = self.maze[x-1][y]

                elif(dir == direction.DOWN):
                    self.maze[x][y].set_down_wall(False)
                    self.maze[x+1][y].set_up_wall(False)
                    current_cell = self.maze[x+1][y]

                elif(dir == direction.LEFT):
                    self.maze[x][y].set_left_wall(False)
                    self.maze[x][y-1].set_right_wall(False)
                    current_cell = self.maze[x-1][y]

                elif(dir == direction.RIGHT):
                    self.maze[x][y].set_right_wall(False)
                    self.maze[x-1][y].set_left_wall(False)
                    current_cell = self.maze[x][y+1]
                self.cell_check.append((current_cell.get_x(),current_cell.get_y()))
                self.stack.append(current_cell)
                visited = visited + 1
            else: 
                self.stack.pop()
                current_cell = self.stack[len(self.stack)-1]
            print(visited)
                


class Cell:

    def __init__(self,x,y):
        self.x = x
        self.y = y
        self.up_wall = True 
        self.down_wall = True
        self.left_wall = True 
        self.right_wall = True
    
    # def __str__(self):
    #     return str(self.get_x) + ""+ str(self.get_y)

    def get_x(self):
        return self.x

    def get_y(self):
        return self.y
    
    def set_up_wall(self,up_wall):
        self.up_wall = up_wall
    
    def set_down_wall(self,down_wall):
        self.down_wall = down_wall
    
    def set_left_wall(self,left_wall):
        self.left_wall = left_wall

    def set_right_wall(self,right_wall):
        self.right_wall = right_wall 
    
    def get_up_wall(self):
        return self.up_wall
    
    def get_down_wall(self):
        return self.down_wall
    
    def get_left_wall(self):
        return self.left_wall

    def get_right_wall(self):
        return self.right_wall

class direction(Enum):

    UP    = 'UP'
    DOWN  = 'DOWN'
    LEFT  = 'LEFT'
    RIGHT = 'RIGHT'

    def __str__(self):
        return "Mince" + self.value









    
