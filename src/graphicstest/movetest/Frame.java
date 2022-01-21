package graphicstest.movetest;

import javax.swing.*;

public class Frame extends JFrame {

//    DragPanel dragPanel;
    ShapePane shapePane;

    Frame() {
        this.setTitle("Demo");
        this.setSize(1920,1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        dragPanel = new DragPanel();
//        this.add(dragPanel);
        shapePane = new ShapePane();
        this.add(shapePane);

        this.setVisible(true);
    }
}
