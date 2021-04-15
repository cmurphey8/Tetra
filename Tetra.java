
/**************************************************************************************************
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
 *  GOAL:   Update all remaining Tetroid*.java files (L, T, Z) so that all tetroids
 *          initialize and rotate properly
 * 
 *  DISCUSSION: Complete the form @ 
 *              (1) What is the significance of our abstract parent class not having a constructor?
 * 
 *              (2) Which methods do you suspect we could have removed from each Tetroid
 *                  child class if our parent was not abstract? 
 * 
 *                  [HINT: check in to method calls in both Tetra and in their own Tetra* class files]
 * 
 *              (2.1)   For those child methods we cannot remove, what is different about them?
 *                      Would they still be usable as is?
 * 
 *              (3) What kinds of methods do you suspect we would need to add to our Shape
 *                  parent class if it were not abstract? 
 * 
 *                  [HINT: refer back to (2.1)]
 * 
 *  EXTRA PRACTICE: Remove the abstract keyword from the Shape parent class 
 *                  and Redesign the program around this update
 * 
 **************************************************************************************************/

public class Tetra { 
    public static Shape[] tetroids;
    public static Shape[] templates;
    
    public static int index;
    public static int gridX = 27;
    public static int gridY = 10;

    public static void main(String[] args) {    
        
        // save up to 100 tetroids
        tetroids = new Shape[100];
        templates = new Shape[7];
        
        // count of total tetroids in play
        index = 0;
        
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

            // process mouse clicks if NO object is selected
            if (StdDraw.isMousePressed() && released) {
                released = selected(x, y);
            }

            // process key entries if objct IS selected
            if (StdDraw.hasNextKeyTyped() && !released) {
                tetroids[index].rotate(StdDraw.nextKeyTyped());
            }

            // process mouse clicks if an object IS selected
            if (StdDraw.isMousePressed() && !released) {
                released = unselected(x, y);
            }

            // follow the mouse while an object is selected
            if (!released) {
                drawBackground(); 
                tetroids[index].hover(x - 0.5, y - 0.5); 
                StdDraw.show(); 
            }
        } // end of while
    } 

    // process mouse clicks if NO object is selected
    public static boolean unselected(double x, double y) {
        if (tetroids[index].inBounds(Math.floor(x), Math.floor(y), gridX, gridY)) {

            // check for overlapping blocks before placing the selected block
            boolean conflict = false;
            for (int i = 0; i < index; i++) {
                if (tetroids[index].overLaps(Math.floor(x), Math.floor(y), tetroids[i].getX(), tetroids[i].getY())) {
                    conflict = true;
                }  
            }

            // place the block by iterating to the next open index of the tetroids array
            if (!conflict) {
                tetroids[index++].hover(Math.floor(x), Math.floor(y));
                tetroids[index] = null;

                drawBackground(); 
                StdDraw.show(); 
                StdDraw.pause(200);
                return true;   
            }
        }
        return false;
    }

    // process mouse clicks if an object IS selected
    public static boolean selected(double x, double y) {

        // add a new block to the tetroids array if a template was clicked on 
        int k = checkTemplates(x, y);
        if (k >= 0) {
            initNew(k, x, y);
            StdDraw.show();
            StdDraw.pause(200);
            return false;
        }
        return true;
    }

    public static int checkTemplates(double x, double y) {
        // check if a template was clicked on
        int k = -1;
        for (int i = 0; i < templates.length; i++) {
            if (templates[i].clicked(Math.floor(x), Math.floor(y))) {
                k = i;
            }
        }
        return k;
    }

    public static void initNew(int k, double x, double y) {
        // add a new block to the tetroids array by the template type identified
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
        // initialize the templates array with all our tetroid block shapes
        templates[0] = new TetroidI(0,-3);
        templates[1] = new TetroidJ(5, -3);
        templates[2] = new TetroidL(9, -3);
        templates[3] = new TetroidO(13,-3);
        templates[4] = new TetroidS(16, -3);
        templates[5] = new TetroidT(20, -3);
        templates[6] = new TetroidZ(24, -2);
    }
    
    // draw the frame, templates, and all existing tetroid blocks
    public static void drawBackground() {
        // frame
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(13.5, 3.5, 14.5, 7.5);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledRectangle(13.5, 5, 13.5, 5);

        StdDraw.setPenColor(StdDraw.GRAY);
        for (int i = 0; i < gridX; i++) {
            for (int j = 0; j < gridY; j++) {
                StdDraw.square(i + 0.5, j + 0.5, 0.5);
            }
        }

        // templates
        for (int i = 0; i < templates.length; i++) {
            templates[i].draw();
        }

        // existing tetroid blocks
        for (int i = 0; i < index; i++) {
            tetroids[i].draw();
        }
    }
} 