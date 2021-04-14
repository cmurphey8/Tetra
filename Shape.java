/**********************************************************************************
 *
 *  cut abstract out to share more methods
 *              
 **********************************************************************************/

import java.awt.Color;

// abstract classes do not get constructors!
public abstract class Shape {

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

    public void hover(double x, double y) {}
    public void draw() {}
    public void rotate(char key) {}
    public boolean clicked(double x, double y) {return false;}
    public boolean inBounds(double x, double y, int gridX, int gridY) {return false;}
}
