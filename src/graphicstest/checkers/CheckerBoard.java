package graphicstest.checkers;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CheckerBoard {

    CheckerBoard(Graphics2D g2d, double modifier, double width, double height, double spacer_height, double blockSize) {
        width = width*modifier;
        height = height*modifier;
        spacer_height = spacer_height*modifier;

        blockSize = blockSize*modifier;
        double y = spacer_height;

        Rectangle2D background = new Rectangle2D.Double(0, 0,width,height);
        g2d.setColor(new Color(161, 111, 32));
        g2d.fill(background);

        Rectangle2D topSpacer = new Rectangle2D.Double(0,0,width,spacer_height);
        g2d.setColor(new Color(96, 67, 16));
        g2d.fill(topSpacer);

        g2d.setColor(new Color(222, 174, 112));

        double widthBlock = (int) (width/(blockSize*2));
        double heightBlock = (int) (height/blockSize);

        System.out.println(heightBlock);

        for(int i = 0; i < heightBlock/2; i++) {
            for (int i2 = 0; i2 < 10; i2++) {
                g2d.fill(new Rectangle2D.Double(1, y,blockSize,blockSize));
                g2d.translate(blockSize*2, 0);
            }
            g2d.translate(-(width-spacer_height), 0);
            y += blockSize;

            for (int i3 = 0; i3 < widthBlock; i3++) {
                g2d.fill(new Rectangle2D.Double(1,y,blockSize,blockSize));
                g2d.translate(blockSize*2, 0);
            }
            g2d.translate(-(width-spacer_height), 0);
            y += blockSize;
            System.out.println(i);
        }

        Rectangle2D lowerSpacer = new Rectangle2D.Double(0,height-(spacer_height*3),width,spacer_height*1.2);
        g2d.setColor(new Color(96, 67, 16));
        g2d.fill(lowerSpacer);
    }
}
