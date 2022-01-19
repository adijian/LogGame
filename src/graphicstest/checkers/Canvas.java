package graphicstest.checkers;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JComponent {
    CheckerBoard checkerBoard;

    public Canvas() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(renderingHints);

        checkerBoard = new CheckerBoard(g2d, 1d, 1920, 1080, 20, 100);
    }
}
