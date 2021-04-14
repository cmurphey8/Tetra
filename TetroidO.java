/**********************************************************************************
 *
 *  This block is complete: a good reference for other blocks!
 *              
 **********************************************************************************/
import java.awt.Color;

public class TetroidO extends Shape {
    private double[] x;         // x position for blocks
    private double[] y;         // y position for blocks
    Color C = StdDraw.YELLOW;   // this tetroid color
                                // no rotation needed for this block

    public TetroidO(double x, double y) {
        this.x = new double[4];
        this.y = new double[4];
        hover(x, y);
    }

    public void hover(double x, double y) {
        for (int i = 0; i < 2; i++) {
            this.x[i] = x + i;
            this.y[i] = y;    
            this.x[i + 2] = x + i;
            this.y[i + 2] = y + 1;  
        }
        draw();
    }

    public void draw() {
        for (int i = 0; i < 4; i++) {
            super.drawSquare(x[i], y[i], C);           
        }
    }

    public boolean clicked(double x, double y) {      
        for (int i = 0; i < 4; i++) {
            if (this.x[i] == x && this.y[i] == y) 
                return true;        
        }
        return false;
    }

    public boolean inBounds(double x, double y, int gridX, int gridY) { 
        hover(x, y);     
        for (int i = 0; i < 4; i++) {
            if (this.x[i] >= gridX || this.x[i] < 0 || this.y[i] >= gridY || this.y[i] < 0) 
                return false;        
        }
        return true;
    }
}
