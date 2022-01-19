package graphicstest.cloudtest;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JComponent {
    private int width;
    private int height;
    private Cloud cloud1;
    private Cloud cloud2;
    private Cloud cloud3;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        cloud1 = new Cloud(10,50,75,Color.LIGHT_GRAY);
        cloud2 = new Cloud(200,75,90,Color.BLUE);
        cloud3 = new Cloud(420,60,85,Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(renderingHints);

        cloud1.drawCloud(g2d);
        cloud2.drawCloud(g2d);
        cloud3.drawCloud(g2d);
    }

}
