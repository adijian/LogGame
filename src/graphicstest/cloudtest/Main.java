package graphicstest.cloudtest;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int width = 1920;
        int height = 1080;

        Canvas canvas = new Canvas(width,height);

        JFrame frame = new JFrame();
        frame.setSize(width,height);
        frame.setTitle("Drawing");

        frame.add(canvas);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
