package graphicstest.cloudtest;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Cloud {
    private double x;
    private double y;
    private double size;
    private Color color;

    public Cloud(double x,double y,double size,Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public void drawCloud(Graphics2D g2d) {
        Ellipse2D.Double ellipse1 = new Ellipse2D.Double(x,y,size,size);
        Ellipse2D.Double ellipse2 = new Ellipse2D.Double(x+size*.35,y-size*.2,size*1.75,size*1.4);
        Ellipse2D.Double ellipse3 = new Ellipse2D.Double(x+size*1.5,y+size*.15,size*.9,size*.9);
        Ellipse2D.Double ellipse4 = new Ellipse2D.Double(x+size*1.8,y+size*.05,size*.3,size*.3);
        g2d.setColor(color);
        g2d.fill(ellipse1);
        g2d.fill(ellipse2);
        g2d.fill(ellipse3);
        g2d.fill(ellipse4);
    }
}
