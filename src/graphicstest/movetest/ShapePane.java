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
    Point[] rectangleCorners;
    Point[] rectanglePreviousPoints;
    Boolean[] isDragValidPerRectangle;
    int i = 0;

    public ShapePane() {

        this.setSize(1920, 1080);

        button = new JButton("Create new rectangle");
        rectangleList = new Rectangle2D.Double[1000];
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
        g2.setColor(Color.blue);
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

            for (int t = 0; t < rectangleList.length; t++) {
                if (rectangleList[t] != null) {
                    if (isDragValidPerRectangle[t]) {
                        Point currentPoint = e.getPoint();
                        rectangleCorners[t].translate(
                                (int) (currentPoint.getX() - rectanglePreviousPoints[t].getX()),
                                (int) (currentPoint.getY() - rectanglePreviousPoints[t].getY())
                        );
                        rectangleList[t].x = rectangleCorners[t].getX();
                        rectangleList[t].y = rectangleCorners[t].getY();
                        rectanglePreviousPoints[t] = currentPoint;
                        repaint();
                    }
                }
            }
        }
    }
}
