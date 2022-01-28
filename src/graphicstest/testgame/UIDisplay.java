package graphicstest.testgame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

class UIDisplay {

    GamePanel gamePanel;
    Font arial_40B;
    Font arial_40;
    JButton gameStartButton;
    Rectangle startButtonRectangle;

    JButton exitToMainMenuButton;

    BufferedImage bearImage;

    UIDisplay(GamePanel gamePanel) throws IOException {
        this.gamePanel = gamePanel;
        arial_40B = new Font("Arial", Font.BOLD, 40);
        arial_40 = new Font("Arial", Font.PLAIN, 40);

        startButtonRectangle = new Rectangle((int) (500),
                (int) (gamePanel.panelHeight / 5),
                (int) (gamePanel.getTILE_SIZE() * 20),
                (int) (gamePanel.getTILE_SIZE() * 2));
        gameStartButton = new JButton();

        exitToMainMenuButton = new JButton();
        bearImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/misc/clipart.png")));

    }

    public void draw(Graphics2D g2d) {

        switch(gamePanel.gameState) {
            case startState:
                // dark gray background
                g2d.setColor(Color.DARK_GRAY);
                g2d.fillRect(0,
                        0,
                        (int)(gamePanel.panelWidth),
                        (int)(gamePanel.panelHeight));


                // display FPS
                g2d.setFont(arial_40);
                g2d.setColor(Color.lightGray);

                String text = "FPS: " + gamePanel.lastDrawCount;

                int x = gamePanel.getTILE_SIZE();
                int y = gamePanel.getTILE_SIZE();
                g2d.drawString(text, x, y);

                // game title
                g2d.setFont(arial_40B);
                String gameName = "Loom Bear Jack";
                int textLength = (int) g2d.getFontMetrics().getStringBounds(gameName, g2d).getWidth();
                int xGameTitle = gamePanel.panelWidth/2 - textLength/2;
                int yGameTitle = gamePanel.getTILE_SIZE();
                g2d.drawString(gameName, xGameTitle, yGameTitle);

                // start game panel
                g2d.fillRect((int)(textLength),
                        (int)(gamePanel.panelHeight/10),
                        (int)(gamePanel.panelWidth * 0.7),
                        (int)(gamePanel.panelHeight * 0.7));

                // start button
                g2d.setColor(Color.DARK_GRAY);
                g2d.fill(startButtonRectangle);

                g2d.setColor(Color.lightGray);
                g2d.setFont(arial_40);
                g2d.drawString("Start Game", (int)(startButtonRectangle.x*1.7), (int)(startButtonRectangle.y*1.28));

                g2d.drawImage(bearImage, (int)(startButtonRectangle.x*1.4),(int)(startButtonRectangle.y*1.8),null);

                break;

            case initialGameState:
                // display FPS
                g2d.setFont(arial_40);
                g2d.setColor(new Color(0,0,0,100));

                text = "FPS: " + gamePanel.lastDrawCount;

                x = gamePanel.TILE_SIZE/2;
                y = gamePanel.TILE_SIZE;
                g2d.drawString(text, x, y);

                // exit to main menu
                text = "Exit";
                x = gamePanel.panelWidth - (gamePanel.TILE_SIZE*2);
                y = gamePanel.TILE_SIZE;
                g2d.drawString(text, x, y);


                break;
        }
    }
}
