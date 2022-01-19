package graphicstest.checkers;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int width = 1920;
        int height = 1080;
        JFrame frame = new JFrame();
        Canvas canvas = new Canvas();
        frame.setSize(width, height);
        frame.setTitle("Graphics");
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
