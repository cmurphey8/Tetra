/**********************************************************************************
 *
 *  TODO: Update Hover() to accomodate the Z tetroid block shape
 *              
 **********************************************************************************/
import java.awt.Color;

public class TetroidZ extends Shape {
    private double[] x;         // x position for blocks
    private double[] y;         // y position for blocks
    private int rotation;       // 2 phases total
    Color C = StdDraw.RED;      // this tetroid color

    public TetroidZ(double x, double y) {
        this.x = new double[4];
        this.y = new double[4];
        rotation = 0;
        hover(x, y);
    }

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

    public void draw() {
        for (int i = 0; i < 4; i++) {
            super.drawSquare(x[i], y[i], C);           
        }
    }

    public void rotate(char key) {
        if (key == 32) rotation = (rotation + 1) % 2;
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
