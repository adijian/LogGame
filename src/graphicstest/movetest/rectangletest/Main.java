package graphicstest.movetest.rectangletest;
import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {


        JFrame frame = new JFrame();
        frame.add(new Panel());

        frame.setTitle("Demo");
        frame.setSize(1920,1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
