package graphicstest.movetest.moveobjecttestnew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ShapePane extends JPanel {
    JButton button;

    MoveableRectangles[] rectangleList;
    Rectangle[] rectanglesIntersactions;
    Point[] rectangleCorners;
    Point[] rectanglePreviousPoints;
    Boolean[] isDragValidPerRectangle;
    int numberOfRectangles = 0;

    public ShapePane() {

        this.setSize(1920, 1080);

        button = new JButton("Create new rectangle");
        rectangleList = new MoveableRectangles[1000];
        rectanglesIntersactions = new Rectangle[1000];


        button.addActionListener(e -> {
            rectangleList[numberOfRectangles] = new MoveableRectangles(100);
            rectangleList[numberOfRectangles].idNumber = numberOfRectangles;
            System.out.println("created rectangle id " + rectangleList[numberOfRectangles].idNumber);
            numberOfRectangles++;
            repaint();
        });
        this.add(button);

        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
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
        g2.setColor(new Color(1+colorChanger,1+colorChanger,1));
        for (MoveableRectangles rectanglelist : rectangleList) {
            if (rectanglelist != null) {
                g2.fill(rectanglelist.rectangle);
            }
        }
    }

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {

            for (MoveableRectangles rectangle : rectangleList) {
                if (rectangle != null) {
                    rectangle.rectanglePreviousPoints = e.getPoint();
                    rectangle.isDragValid = (e.getPoint().getX() > rectangle.topLeftCornerPoint.getX()) &&
                            (e.getPoint().getX() < (rectangle.topLeftCornerPoint.getX() + rectangle.rectangle.getWidth())) &&
                            (e.getPoint().getY() > rectangle.topLeftCornerPoint.getY()) &&
                            (e.getPoint().getY() < (rectangle.topLeftCornerPoint.getY() + rectangle.rectangle.getHeight()));
                    System.out.println("dragg");
                }
            }
        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {

            for (MoveableRectangles rectangle : rectangleList) {
                if (rectangle != null) {
                    if (rectangle.isDragValid) {
                        Point currentPoint = e.getPoint();
                        rectangle.topLeftCornerPoint.translate(
                                (int) (currentPoint.getX() - rectangle.rectanglePreviousPoints.getX()),
                                (int) (currentPoint.getY() - rectangle.rectanglePreviousPoints.getY())
                        );

                        rectangle.rectangle.x = (int) rectangle.topLeftCornerPoint.getX();
                        rectangle.rectangle.y = (int) rectangle.topLeftCornerPoint.getY();
                        rectangle.rectanglePreviousPoints = currentPoint;
                        repaint();

                        for(int j = 0; j < rectangleList.length; j++) {
                            if (rectangle.idNumber != j && rectangleList[j] != null) {

                                if(rectangle.rectangle.intersects(rectangleList[j].rectangle)) {
                                    rectanglesIntersactions[j] = new Rectangle(
                                            (int) rectangle.rectangle.createIntersection(rectangleList[j].rectangle).getX(),
                                            (int) rectangle.rectangle.createIntersection(rectangleList[j].rectangle).getY(),
                                            (int) rectangle.rectangle.createIntersection(rectangleList[j].rectangle).getWidth(),
                                            (int) rectangle.rectangle.createIntersection(rectangleList[j].rectangle).getHeight());

                                    if(rectanglesIntersactions[j].x > 0 && rectanglesIntersactions[j].y > 0){
                                        rectangle.topLeftCornerPoint.x += (int) (rectangle.rectangle.x - rectanglesIntersactions[j].x)/10;
                                        rectangle.topLeftCornerPoint.y += (int) (rectangle.rectangle.y - rectanglesIntersactions[j].y)/10;

                                        rectangle.rectangle.x = rectangle.topLeftCornerPoint.x;
                                        rectangle.rectangle.y = rectangle.topLeftCornerPoint.y;
                                        rectanglesIntersactions[j] = null;

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