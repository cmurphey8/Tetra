
/*************************************************************************
 *  Compilation:  javac Tetra.java
 *  Execution:    java Tetra
 *  Dependencies: StdDraw.java Shape.java Tetroid*.java
 * 
 *  Usage:  Pick up a copy of one of the tetroid templates by clicking on it;
 *          Rotate a picked up object with space bar;
 *          Set a picked up object by clicking in the black and grey grid;
 * 
 *  NOTE:   Up to 100 copy-blocks can be created, per the tetroids array initialization
 *          Copy-blocks may overlap with the current configuration - we'll worry about this later!
 * 
 *  PART 1: update all remaining Tetroid*.java files (L, T, Z) so that all tetroids
 *          initialize and rotate properly
 * 
 *  PART 2: update tetra.java such that copy-blocks can be picked up and moved
 *          you should not need to make any further changes to the Tetroid*.java files for this update!
 * 
 *************************************************************************/

public class Tetra { 
    public static Shape[] tetroids;
    public static Shape[] templates;
    
    public static int index;

    public static void main(String[] args) {    
        
        // save up to 100 tetroids
        tetroids = new Shape[100];
        templates = new Shape[7];
        
        // count of total tetroids in play
        index = 0;
        int gridX = 27;
        int gridY = 10;
        // initialize StdDraw elements
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(640, 300);
        StdDraw.setXscale(-1, 28.0);
        StdDraw.setYscale(-4, 11.0);

        initTemplates();
        drawBackground();
        StdDraw.show();

        boolean released = true;
        while (true) {
            double x = StdDraw.mouseX();
            double y = StdDraw.mouseY();

            if (StdDraw.isMousePressed() && released) {
                int k = checkTemplates(x, y);
                if (k >= 0) {
                    initNew(k, x, y);
                    StdDraw.show();
                    StdDraw.pause(200);
                    released = false;
                }
            }

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped() && !released) {
                tetroids[index].rotate(StdDraw.nextKeyTyped());
            }

            if (StdDraw.isMousePressed() && !released) {
                if (tetroids[index].inBounds(Math.floor(x), Math.floor(y), gridX, gridY)) {
                    boolean conflict = false;
                    for (int i = 0; i < index; i++) {
                        System.out.println(i);
                        if (tetroids[index].overLaps(Math.floor(x), Math.floor(y), tetroids[i].xArr(), tetroids[i].yArr())) {
                            conflict = true;
                        }  
                    }
                    if (!conflict) {
                        tetroids[index++].hover(Math.floor(x), Math.floor(y));
                        tetroids[index] = null;
        
                        drawBackground(); 
                        StdDraw.show(); 
                        StdDraw.pause(200);
                        released = true;   
                    }
                }
            }

            if (tetroids[index] != null) {
                drawBackground(); 
                tetroids[index].hover(x - 0.5, y - 0.5); 
                StdDraw.show(); 
            }
        } // end of while
    } 

    public static int checkTemplates(double x, double y) {
        int k = -1;
        for (int i = 0; i < templates.length; i++) {
            if (templates[i].clicked(Math.floor(x), Math.floor(y))) {
                k = i;
            }
        }
        return k;
    }

    public static void initNew(int k, double x, double y) {
        switch (k) {
            case 0: 
                tetroids[index] = new TetroidI(x - 0.5, y - 0.5);
                break;
            case 1: 
                tetroids[index] = new TetroidJ(x - 0.5, y - 0.5);
                break;
            case 2: 
                tetroids[index] = new TetroidL(x - 0.5, y - 0.5);
                break;
            case 3: 
                tetroids[index] = new TetroidO(x - 0.5, y - 0.5);
                break;
            case 4: 
                tetroids[index] = new TetroidS(x - 0.5, y - 0.5);
                break;
            case 5: 
                tetroids[index] = new TetroidT(x - 0.5, y - 0.5);
                break;
            case 6:
                tetroids[index] = new TetroidZ(x - 0.5, y - 0.5);
                break;
        }
    }

    public static void initTemplates() {
        templates[0] = new TetroidI(0,-3);
        templates[1] = new TetroidJ(5, -3);
        templates[2] = new TetroidL(9, -3);
        templates[3] = new TetroidO(13,-3);
        templates[4] = new TetroidS(16, -3);
        templates[5] = new TetroidT(20, -3);
        templates[6] = new TetroidZ(24, -3);
    }
    
    // draw the billiards table with all remaining balls
    public static void drawBackground() {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(13.5, 3.5, 14.5, 7.5);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledRectangle(13.5, 5, 13.5, 5);

        StdDraw.setPenColor(StdDraw.GRAY);
        for (int i = 0; i < 27; i++) {
            for (int j = 0; j < 10; j++) {
                StdDraw.square(i + 0.5, j + 0.5, 0.5);
            }
        }
        for (int i = 0; i < index; i++) {
            tetroids[i].draw();
        }

        for (int i = 0; i < templates.length; i++) {
            templates[i].draw();
        }
    }
} 