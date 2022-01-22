package graphicstest.movetest.moveobjecttest;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Panel extends JPanel{

    JButton button;
    CustomRectangles[] rectanglesList;
    int initial;
    int numberOfRectangles;

    Rectangle[] rectanglesIntersactions;

    Panel() {
        initial = 100;
        numberOfRectangles = 0;

        rectanglesList = new CustomRectangles[100];
        rectanglesIntersactions = new Rectangle[100];

        button = new JButton("Create new rectangle");

        button.addActionListener(e -> {
            rectanglesList[numberOfRectangles] = new CustomRectangles(initial);
            rectanglesList[numberOfRectangles].idNumber = numberOfRectangles;
            System.out.println("created rectangle id " + rectanglesList[numberOfRectangles].idNumber);
            numberOfRectangles++;
            repaint();
        });
        this.add(button);


        Panel.ClickListener clickListener = new Panel.ClickListener();
        Panel.DragListener dragListener = new Panel.DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        for (CustomRectangles rectangleslist : rectanglesList) {
            if (rectangleslist != null) {
                g2d.fill(rectangleslist.getRectangle2d());
            }
        }
    }

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            for (CustomRectangles rectangles : rectanglesList) {
                if (rectangles != null) {
                    rectangles.rectanglePreviousPoints = e.getPoint();
                    rectangles.isDragValid = (e.getPoint().getX() > rectangles.topRightCornerPoint.x &&
                            e.getPoint().getX() < (rectangles.topRightCornerPoint.x + rectangles.getRectangle2d().width) &&
                            e.getPoint().getY() > rectangles.topRightCornerPoint.y &&
                            e.getPoint().getY() < (rectangles.topRightCornerPoint.y + rectangles.getRectangle2d().height));
                }
            }

        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {

            for (CustomRectangles rectangles : rectanglesList) {
                if (rectangles != null) {
                    if(rectangles.isDragValid) {
                        Point currentPoint = e.getPoint();
                        rectangles.topRightCornerPoint.translate(
                                (int) (currentPoint.getX() - rectangles.rectanglePreviousPoints.getX()),
                                (int) (currentPoint.getY() - rectangles.rectanglePreviousPoints.getY())
                        );
                        rectangles.getRectangle2d().x = rectangles.topRightCornerPoint.x;
                        rectangles.getRectangle2d().y = rectangles.topRightCornerPoint.y;
                        rectangles.topRightCornerPoint = currentPoint;
                        repaint();

                        System.out.println(rectangles);//debug
                        System.out.println(rectangles.topRightCornerPoint);//debug

                            for(int j = 0; j < rectanglesList.length; j++) {
                                if( rectangles.idNumber != j && rectanglesList[j] != rectangles && rectanglesList[j] != null) {
                                    if(rectangles.getRectangle2d().intersects(rectanglesList[j].getRectangle2d())) {
                                        rectanglesIntersactions[j] = new Rectangle(
                                                (int) rectangles.getRectangle2d().createIntersection(rectanglesList[j].getRectangle2d()).getX(),
                                                (int) rectangles.getRectangle2d().createIntersection(rectanglesList[j].getRectangle2d()).getY(),
                                                (int) rectangles.getRectangle2d().createIntersection(rectanglesList[j].getRectangle2d()).getWidth(),
                                                (int) rectangles.getRectangle2d().createIntersection(rectanglesList[j].getRectangle2d()).getHeight());

                                        if(rectanglesIntersactions[j].x > 0 && rectanglesIntersactions[j].y > 0) {
                                            rectanglesList[j].topRightCornerPoint.x += (int) (rectanglesList[j].getRectangle2d().x - rectanglesIntersactions[j].x)/10;
                                            rectanglesList[j].topRightCornerPoint.y += (int) (rectanglesList[j].getRectangle2d().y - rectanglesIntersactions[j].y)/10;

                                            rectanglesList[j].getRectangle2d().x = rectanglesList[j].topRightCornerPoint.x;
                                            rectanglesList[j].getRectangle2d().y = rectanglesList[j].topRightCornerPoint.y;
                                            rectanglesIntersactions[j] = null;

                                            if(rectanglesList[j].getRectangle2d().x > 1920) {
                                                rectanglesList[j].topRightCornerPoint.x = (int) (1920 - rectanglesList[j].getRectangle2d().x);
                                                rectanglesList[j].getRectangle2d().x = rectanglesList[j].topRightCornerPoint.x;
                                            }

                                            if(rectanglesList[j].getRectangle2d().x > 1080) {
                                                rectanglesList[j].topRightCornerPoint.x = (int) (1080 - rectanglesList[j].getRectangle2d().x);
                                                rectanglesList[j].getRectangle2d().x = rectanglesList[j].topRightCornerPoint.x;
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
