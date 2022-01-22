package graphicstest.movetest.moveobjecttest;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CustomRectangles {

    Rectangle2D.Double rectangle2d;
    Point topRightCornerPoint;
    Point bottomLeftCornerPoint;
    Point rectanglePreviousPoints;
    Boolean isDragValid = false;
    int initialX, initialY, initialWidth, initialHeight;
    int idNumber;


    CustomRectangles(int initial) {
        initialX = initial;
        initialY = initial;
        initialWidth = initial;
        initialHeight = initial;
        rectanglePreviousPoints = new Point(0,0);

        topRightCornerPoint = new Point(initialX,initialY);
        bottomLeftCornerPoint = new Point(topRightCornerPoint.x + initialWidth,topRightCornerPoint.y + initialHeight); // x = width, y = height
        rectangle2d = new Rectangle2D.Double(topRightCornerPoint.x,topRightCornerPoint.y,bottomLeftCornerPoint.x,bottomLeftCornerPoint.y);
    }

    public Rectangle2D.Double getRectangle2d() {
        return rectangle2d;
    }
}
