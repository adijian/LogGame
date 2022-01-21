package graphicstest.movetest.rectangletest;

import javax.swing.*;
import java.awt.*;
//import java.lang.annotation.Retention;

public class Panel extends JPanel {
//    Rectangle rect1;
//    Rectangle rect2;
//    Rectangle rect3;
//    Rectangle intersection;
//    Rectangle intersection2;

    Rectangle[] rectangles;
    Rectangle[] rectanglesIntersactions;
    int colorChange;


    Panel() {
        rectangles = new Rectangle[10];
        rectanglesIntersactions = new Rectangle[10];

        rectangles[0] = new Rectangle(100,100,100,100);
        rectangles[1] = new Rectangle(120,100,100,100);

        for(int i = 0; i < rectangles.length; i++) {
            if(rectangles[i] != null) {
                for(int j = 0; j < rectangles.length; j++) {
                    if (i != j && rectangles[j] != null) {
                        if(rectangles[i].intersects(rectangles[j])) {
                            rectanglesIntersactions[i] = new Rectangle(rectangles[i].intersection(rectangles[j]));
                            if(rectanglesIntersactions[i].x > 0 && rectangles[i].y > 0) {
                                rectangles[i].x += rectanglesIntersactions[i].x;
                                rectangles[i].y += rectanglesIntersactions[i].y;
                                rectanglesIntersactions[i] = null;
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for(Rectangle rectangle : rectangles) {
            if(rectangle != null) {
                g2.setColor(new Color(1+colorChange,1+colorChange,1));
                g2.fill(rectangle);
                colorChange += 30;
            }
        }

        for(Rectangle rectangleintersactions : rectanglesIntersactions) {
            if(rectangleintersactions != null) {
                g2.setColor(new Color(1,1, 1));
                g2.draw(rectangleintersactions);
            }
        }
    }
}
