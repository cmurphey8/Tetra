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
    Color C = StdDraw.ORANGE;   // this tetroid color

    public TetroidL(double x, double y) {
        this.x = new double[4];
        this.y = new double[4];
        rotation = 0;
        hover(x, y);
    }

    public void hover(double x, double y) {
        switch (rotation) {
            case 0:
                for (int i = 0; i < 3; i++) {
                    this.x[i] = x + i;
                    this.y[i] = y;    
                }
                this.x[3] = x + 2;
                this.y[3] = y + 1;
                break;
            case 1:
                for (int i = 0; i < 3; i++) {
                    this.x[i] = x;
                    this.y[i] = y - i;    
                }
                this.x[3] = x + 1;
                this.y[3] = y - 2;
                break;
            case 2: 
                for (int i = 0; i < 3; i++) {
                    this.x[i] = x - i;
                    this.y[i] = y;    
                }
                this.x[3] = x - 2;
                this.y[3] = y - 1;
                break;
            case 3:
                for (int i = 0; i < 3; i++) {
                    this.x[i] = x;
                    this.y[i] = y + i;    
                }
                this.x[3] = x - 1;
                this.y[3] = y + 2;
                break;
        }
        draw();
    }

    public void draw() {
        for (int i = 0; i < 4; i++) {
            super.drawSquare(x[i], y[i], C);           
        }
    }

    public void rotate(char key) {
        if (key == 32) rotation = (rotation + 1) % 4;
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

    public boolean overLaps(double x, double y, double[] xTest, double[] yTest) { 
        hover(x, y);     
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if (this.x[i] == xTest[j] && this.y[i] == yTest[j]) 
                    return true;  
            }    
        }
        return false;
    }

    public double[] xArr() { 
        return this.x;
    }
    
    public double[] yArr() { 
        return this.y;
    }
}
