/**********************************************************************************
 *
 *  cut abstract out to share more methods
 *              
 **********************************************************************************/

import java.awt.Color;

// abstract classes do not get constructors!
public abstract class Shape {

    public static void draw(double[] x, double[] y, Color C) {
        for (int i = 0; i < 4; i++) {
            drawSquare(x[i], y[i], C);           
        }
    }

    public static void drawSquare(double x, double y, Color C) {
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

    public static boolean overLaps(double[] x, double[] y, double[] xTest, double[] yTest) {
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if (x[i] == xTest[j] && y[i] == yTest[j]) 
                    return true;  
            }    
        }
        return false;
    }

    public static boolean inBounds(double[] x, double[] y, int gridX, int gridY) {
        for (int i = 0; i < 4; i++) {
            if (x[i] >= gridX || x[i] < 0 || y[i] >= gridY || y[i] < 0) 
                return false;        
        }
        return true;
    }

    public static boolean clicked(double[] x, double[] y, double testX, double testY) {
        for (int i = 0; i < 4; i++) {
            if (x[i] == testX && y[i] == testY) 
                return true;        
        }
        return false;
    }

    public abstract void hover(double x, double y);
    public abstract void draw();
    public abstract void rotate(char key);
    public abstract boolean clicked(double x, double y);
    public abstract boolean inBounds(double x, double y, int gridX, int gridY);
    public abstract boolean overLaps(double x, double y, double[] xTest, double[] yTest);
    public abstract double[] xArr();
    public abstract double[] yArr();
}
