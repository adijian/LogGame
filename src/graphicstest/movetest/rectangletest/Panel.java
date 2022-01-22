package graphicstest.movetest.rectangletest;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Panel extends JPanel {
    Rectangle2D.Double[] rectangles;
    Rectangle2D.Double[] rectanglesIntersactions;
    int colorChange;

    Panel() {
        rectangles = new Rectangle2D.Double[10];
        rectanglesIntersactions = new Rectangle2D.Double[100];

        rectangles[0] = new Rectangle2D.Double(100,100,100,100);
        rectangles[1] = new Rectangle2D.Double(120,100,100,100);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for(Rectangle2D.Double rectangle : rectangles) {
            if(rectangle != null) {
                g2.setColor(new Color(1+colorChange,1+colorChange,1));
                g2.fill(rectangle);
                colorChange += 40;
            }
        }

        for(Rectangle2D.Double rectangleintersactions : rectanglesIntersactions) {
            if(rectangleintersactions != null) {
                g2.setColor(new Color(1,1, 1));
                g2.draw(rectangleintersactions);
            }
        }


        for(int i = 0; i < rectangles.length; i++) {
            for(int j = 0; j < rectangles.length; j++) {
                if (i != j && rectangles[j] != null && rectangles[i] != null) {
                    if(rectangles[i].intersects(rectangles[j])) {
//                        rectanglesIntersactions[i] = new Rectangle(rectangles[i].intersection(rectangles[j]));
                        rectanglesIntersactions[i] = new Rectangle2D.Double(rectangles[i].createIntersection(rectangles[j]).getX(),
                                rectangles[i].createIntersection(rectangles[j]).getY(),
                                rectangles[i].createIntersection(rectangles[j]).getWidth(),
                                rectangles[i].createIntersection(rectangles[j]).getHeight());
                        if(rectanglesIntersactions[i].x > 0 && rectangles[i].y > 0) {
                            rectangles[i].x += rectanglesIntersactions[i].x;
                            rectangles[i].y += rectanglesIntersactions[i].y;
                            rectanglesIntersactions[i] = null;
                            repaint();
                        }
                    }
                }
            }
        }
    }
}
