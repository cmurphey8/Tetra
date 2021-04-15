/**********************************************************************************
 *
 *  TODO: Update Hover() and rotate() to accomodate the L tetroid block shape
 *              
 **********************************************************************************/
import java.awt.Color;

public class TetroidL extends Shape {
    private double[] x;         // x position for blocks
    private double[] y;         // y position for blocks
    private int rotation;       // 4 phases total
    private final Color C = StdDraw.ORANGE;   // this tetroid color

    //******************************************************************
    //  CONSTRUCTORS
    //*******************************************************************/

    public TetroidL(double x, double y) {
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

    public void draw() {
        draw(x, y, C);
    }

    public boolean clicked(double x, double y) {     
        return clicked(this.x, this.y, x, y);
    }

    public boolean inBounds(double x, double y, int gridX, int gridY) { 
        hover(x, y);     
        return inBounds(this.x, this.y, gridX, gridY);
    }

    public boolean overLaps(double x, double y, double[] xTest, double[] yTest) { 
        hover(x, y);   
        return overLaps(this.x, this.y, xTest, yTest);
    }

    public double[] xArr() { 
        return this.x;
    }
    
    public double[] yArr() { 
        return this.y;
    }
}
