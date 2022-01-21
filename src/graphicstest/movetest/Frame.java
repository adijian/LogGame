package graphicstest.movetest;

import javax.swing.*;

public class Frame extends JFrame {

    DragPanel dragPanel = new DragPanel();

    Frame() {
        this.add(dragPanel);
        this.setTitle("Demo");
        this.setSize(1920,1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
