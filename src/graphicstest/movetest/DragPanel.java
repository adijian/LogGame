package graphicstest.movetest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;

public class DragPanel extends JPanel {

    Point rectangleCorner;
    Point previousPoint;
    Rectangle2D.Double rectangle;
    int borderPanel;
    boolean dragValid = false;
    ShapePane shapePane;

    DragPanel() {
        this.setSize(1920,1080);
        rectangleCorner = new Point(0, 0);
        borderPanel = 200;
        rectangle = new Rectangle2D.Double(borderPanel + rectangleCorner.getX(), borderPanel + rectangleCorner.getY(), 300, 300);
        shapePane = new ShapePane();
        this.add(shapePane);

        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fill(rectangle);
    }

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            previousPoint = e.getPoint();

            dragValid = (e.getPoint().getX() > rectangleCorner.getX()) &&
                    (e.getPoint().getX() < (rectangleCorner.getX() + rectangle.getWidth())) &&
                    (e.getPoint().getY() > rectangleCorner.getY()) &&
                    (e.getPoint().getY() < (rectangleCorner.getY() + rectangle.getHeight()));
        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            if (dragValid) {
                Point currentPoint = e.getPoint();
                rectangleCorner.translate(
                        (int) (currentPoint.getX() - previousPoint.getX()),
                        (int) (currentPoint.getY() - previousPoint.getY())
                );
                rectangle.x = rectangleCorner.getX();
                rectangle.y = rectangleCorner.getY();
                previousPoint = currentPoint;
                repaint();
            }
        }
    }
}
