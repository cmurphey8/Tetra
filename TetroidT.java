/**********************************************************************************
 *
 *  TODO: Update Hover() and rotate() to accomodate the T tetroid block shape
 *              
 **********************************************************************************/
import java.awt.Color;

public class TetroidT extends Shape {
    private double[] x;         // x position for blocks
    private double[] y;         // y position for blocks
    private int rotation;       // 3 phases total
    private final Color C = StdDraw.MAGENTA;  // this tetroid color

    //******************************************************************
    //  CONSTRUCTORS
    //*******************************************************************/

    public TetroidT(double x, double y) {
        this.x = new double[4];
        this.y = new double[4];
        rotation = 0;
        hover(x, y);
    }

    //******************************************************************
    //  MUTATORS
    //*******************************************************************/

    public void hover(double x, double y) {
        if (rotation == 0) {
            for (int i = 0; i < 4; i++) {
                this.x[i] = x + i;
                this.y[i] = y;    
            }
        }
        else {
            for (int i = 0; i < 4; i++) {
                this.x[i] = x;
                this.y[i] = y + i;    
            }
        }
        draw();
    }

    public void rotate(char key) {
        if (key == 32) rotation = (rotation + 1) % 2;
    }
  
    //******************************************************************
    //  ACCESSORS
    //*******************************************************************/

    public double[] getX() { 
        return this.x;
    }
    
    public double[] getY() { 
        return this.y;
    }

    public Color getC(){
        return this.C;
    }
}
