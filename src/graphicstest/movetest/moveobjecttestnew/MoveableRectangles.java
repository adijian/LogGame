package graphicstest.movetest.moveobjecttestnew;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MoveableRectangles {
    Rectangle2D.Double rectangle;
    Point topLeftCornerPoint;
    Point bottomLeftCornerPoint;
    Point rectanglePreviousPoints;
    Boolean isDragValid = false;
    int initialX, initialY, initialWidth, initialHeight;
    int idNumber;


    MoveableRectangles(int initial) {
        initialX = initial;
        initialY = initial;
        initialWidth = initial;
        initialHeight = initial;
        rectanglePreviousPoints = new Point(0,0);

        topLeftCornerPoint = new Point(initialX,initialY);
        bottomLeftCornerPoint = new Point(topLeftCornerPoint.x + initialWidth,topLeftCornerPoint.y + initialHeight); // x = width, y = height
        rectangle = new Rectangle2D.Double(topLeftCornerPoint.x,topLeftCornerPoint.y,bottomLeftCornerPoint.x,bottomLeftCornerPoint.y);
    }

    public Rectangle2D.Double getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle2D.Double rectangle) {
        this.rectangle = rectangle;
    }

    public Point gettopLeftCornerPoint() {
        return topLeftCornerPoint;
    }

    public void settopLeftCornerPoint(Point topLeftCornerPoint) {
        this.topLeftCornerPoint = topLeftCornerPoint;
    }
}
