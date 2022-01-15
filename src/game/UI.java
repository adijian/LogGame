package game;

import object.ObjectKey;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gamePanel;
    Font arial_40, arial_50B;
    BufferedImage keyImage;
    boolean messageOn = false;
    String message = "";
    int messageCounter = 0;
    boolean gameFinished = false;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_50B = new Font("Arial", Font.BOLD, 50);
        ObjectKey key = new ObjectKey();
        keyImage = key.getImage();
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        if(gameFinished) {
            g2.setFont(arial_50B);
            g2.setColor(Color.YELLOW);
            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gamePanel.screenWidth/2 - textLength/2;
            y = gamePanel.screenHeight/2 - (gamePanel.getTileSize() * 3);
            g2.drawString(text, x, y);

            gamePanel.gameThread = null;}

        else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gamePanel.getTileSize()/2, gamePanel.getTileSize()/2, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            g2.drawString(String.valueOf("x " + gamePanel.getPlayer().getHasKey()), 74,65);

            if(messageOn) {
                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString(message, gamePanel.getTileSize()/2, gamePanel.getTileSize() * 5);

                messageCounter ++;

                if(messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

    public boolean isMessageOn() {
        return messageOn;
    }

    public String getMessage() {
        return message;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
}
