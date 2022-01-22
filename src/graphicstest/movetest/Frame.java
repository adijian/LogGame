package graphicstest.movetest;

import javax.swing.*;

public class Frame extends JFrame {

    ShapePane shapePane;

    Frame() {
        this.setTitle("Demo");
        this.setSize(1920,1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        shapePane = new ShapePane();
        this.add(shapePane);

        this.setVisible(true);
    }
}
