package graphicstest.movetest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;

public class ShapePane extends JPanel {
    JButton button;

    Rectangle2D.Double[] rectangleList;
    Rectangle2D.Double[] rectanglesIntersactions;
    Point[] rectangleCorners;
    Point[] rectanglePreviousPoints;
    Boolean[] isDragValidPerRectangle;
    int i = 0;

    public ShapePane() {

        this.setSize(1920, 1080);

        button = new JButton("Create new rectangle");
        rectangleList = new Rectangle2D.Double[1000];
        rectanglesIntersactions = new Rectangle2D.Double[1000];
        rectangleCorners = new Point[1000];
        rectanglePreviousPoints = new Point[1000];
        isDragValidPerRectangle = new Boolean[1000];
        button.addActionListener(e -> {
            isDragValidPerRectangle[i] = false;
            rectangleCorners[i] = new Point(0, 0);
            rectanglePreviousPoints[i] = new Point(0, 0);
            rectangleList[i] = new Rectangle2D.Double(rectangleCorners[i].getX(), rectangleCorners[i].getY(), 100, 100);
            repaint();
            i++;
        });
        this.add(button);

        ShapePane.ClickListener clickListener = new ShapePane.ClickListener();
        ShapePane.DragListener dragListener = new ShapePane.DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int colorChanger = 50;
        colorChanger += 30;
//        g2.setColor(Color.blue);
        g2.setColor(new Color(1+colorChanger,1+colorChanger,1));
        for (Rectangle2D.Double rectanglelist : rectangleList) {
            if (rectanglelist != null) {
                g2.fill(rectanglelist);
            }
        }
    }

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {

            for (int t = 0; t < rectangleList.length; t++) {
                if (rectangleList[t] != null) {
                    rectanglePreviousPoints[t] = e.getPoint();
                    isDragValidPerRectangle[t] = (e.getPoint().getX() > rectangleCorners[t].getX()) &&
                            (e.getPoint().getX() < (rectangleCorners[t].getX() + rectangleList[t].getWidth())) &&
                            (e.getPoint().getY() > rectangleCorners[t].getY()) &&
                            (e.getPoint().getY() < (rectangleCorners[t].getY() + rectangleList[t].getHeight()));
                }
            }
        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {

            for (int i = 0; i < rectangleList.length; i++) {
                if (rectangleList[i] != null) {
                    if (isDragValidPerRectangle[i]) {
                        Point currentPoint = e.getPoint();
                        rectangleCorners[i].translate(
                                (int) (currentPoint.getX() - rectanglePreviousPoints[i].getX()),
                                (int) (currentPoint.getY() - rectanglePreviousPoints[i].getY())
                        );
                        rectangleList[i].x = rectangleCorners[i].getX();
                        rectangleList[i].y = rectangleCorners[i].getY();
                        rectanglePreviousPoints[i] = currentPoint;
                        repaint();

                        for(int j = 0; j < rectangleList.length; j++) {
                            if (i != j && rectangleList[j] != null && rectangleList[i] != null) {

                                if(rectangleList[i].intersects(rectangleList[j])) {
                                    rectanglesIntersactions[i] = new Rectangle2D.Double(
                                            rectangleList[i].createIntersection(rectangleList[j]).getX(),
                                            rectangleList[i].createIntersection(rectangleList[j]).getY(),
                                            rectangleList[i].createIntersection(rectangleList[j]).getWidth(),
                                            rectangleList[i].createIntersection(rectangleList[j]).getHeight());

                                    if(rectanglesIntersactions[i].x > 0 && rectanglesIntersactions[i].y > 0){
                                        rectangleCorners[i].x += (int) (rectangleList[i].x - rectanglesIntersactions[i].x)/10;
                                        rectangleCorners[i].y += (int) (rectangleList[i].y - rectanglesIntersactions[i].y)/10;

                                        rectangleList[i].x = rectangleCorners[i].x;
                                        rectangleList[i].y = rectangleCorners[i].y;
                                        rectanglesIntersactions[i] = null;

                                        if(rectangleList[i].x >= 1920) {
                                            rectangleCorners[i].x = (int) (1920 - rectangleList[i].x);
                                            rectangleList[i].x = rectangleCorners[i].x;
                                        }

                                        if(rectangleList[i].y >= 1080) {
                                            rectangleCorners[i].y = (int) (1080 - rectangleList[i].y);
                                            rectangleList[i].y = rectangleCorners[i].y;
                                        }
                                        repaint();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}