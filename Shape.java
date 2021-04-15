/**********************************************************************************
 *
 *  cut abstract out to share more methods
 *              
 **********************************************************************************/

import java.awt.Color;

// abstract classes do not get constructors!
public abstract class Shape {

    private static double[] x;
    private static double[] y;
    private static Color C;
    
    public void draw() {
        x = getX();
        y = getY();
        C = getC();
        for (int i = 0; i < 4; i++) {
            drawSquare(x[i], y[i]);           
        }
    }

    public void drawSquare(double x, double y) {
        C = getC();
        StdDraw.setPenColor(C);
        StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5); 

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.square(x + 0.5, y + 0.5, 0.5); 

        StdDraw.line(x, y, x+1, y+1);
        StdDraw.line(x, y+1, x+1, y);
        
        StdDraw.setPenColor(C);
        StdDraw.filledSquare(x + 0.5, y + 0.5, 0.3);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.square(x + 0.5, y + 0.5, 0.3);   
    }

    public boolean overLaps(double x0, double y0, double[] xTest, double[] yTest) {
        hover(x0, y0);
        x = getX();
        y = getY();
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if (x[i] == xTest[j] && y[i] == yTest[j]) 
                    return true;  
            }    
        }
        return false;
    }

    public boolean inBounds(double x0, double y0, int gridX, int gridY) {
        hover(x0, y0);
        x = getX();
        y = getY();
        for (int i = 0; i < 4; i++) {
            if (x[i] >= gridX || x[i] < 0 || y[i] >= gridY || y[i] < 0) 
                return false;        
        }
        return true;
    }

    public boolean clicked(double testX, double testY) {
        x = getX();
        y = getY();
        for (int i = 0; i < 4; i++) {
            if (x[i] == testX && y[i] == testY) 
                return true;        
        }
        return false;
    }

    public abstract void hover(double x, double y);
    public abstract void rotate(char key);
    public abstract double[] getX();
    public abstract double[] getY();
    public abstract Color getC();
}
