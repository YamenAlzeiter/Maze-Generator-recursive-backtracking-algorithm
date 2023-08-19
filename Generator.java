package assignment;

import java.util.Random;

public class Generator {
    private int x = 9;
    private int y = 9;
    private int[][] maze;


    public Generator(){
        this.x = x;
        this.y = y;
        maze = new int[x][y];
        //chosing a starting point which is thr most top left
        generatorMaze(  0 ,0);
    }
    public void printMaze(){
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print((maze[j][i] & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");
            for (int j = 0; j < x; j++) {
                System.out.print((maze[j][i] & 8) == 0 ? "|   " : "    ");
            }
            System.out.println("|");
        }
        for (int j = 0; j < x; j++) {
            System.out.print("+---");
        }
        System.out.println("+");

    }

    private void generatorMaze(int currentX, int currentY){
        int nx, ny;
        var random = new Random();
        for(int k = 0; k< 4; k++){
            //generating a number between 0 and 4 which represent the ransom direction the program gonna take
            int r = random.nextInt(4);
             for(int q = 0; q < 4;q++){
           switch (r) {
               //up
               case 0:
                   nx = currentX;
                   ny = currentY - 1;
                   if (between(nx, x) && between(ny, y) && maze[nx][ny] == 0) {
                       //curving the passage
                       maze[currentX][currentY] |=1;
                       maze[nx][ny] |= 2;
                       //passing the new cell as the current cell and start again
                       generatorMaze(nx, ny);
                       break;
                   }
                   //right
               case 1:
                   nx = currentX + 1;
                   ny = currentY;
                   if (between(nx, x) && between(ny, y) && maze[nx][ny] == 0) {
                       maze[currentX][currentY] |= 4;
                       maze[nx][ny] |= 8;
                       generatorMaze(nx, ny);
                       break;
                   }
                   //down
               case 2:
                       nx = currentX;
                   ny = currentY + 1;
                   if (between(nx, x) && between(ny, y) && maze[nx][ny] == 0) {
                       maze[currentX][currentY] |= 2;
                       maze[nx][ny] |= 1;
                       generatorMaze(nx, ny);
                       break;
                   }
                   //left
               case 3:
                       nx = currentX - 1;
                       ny = currentY;
                   if (between(nx, x) && between(ny, y) && maze[nx][ny] == 0) {
                       maze[currentX][currentY] |= 8;
                       maze[nx][ny] |= 4;
                       generatorMaze(nx, ny);
                       break;
                   }

               default:
                   r = 0;
                   break;
               }
           }
        }
    }
    //check if the cell betwwen the maze borders
    private static boolean between(int v, int upper){
        return (v >= 0) && (v < upper);
    }
}


