package graphicstest.testgame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

class UIDisplay {

    MainGamePanel mainGamePanel;
    Font arial_40B;
    Font arial_40;
    Font arial_20;
    JButton gameStartButton;
    Rectangle startButtonRectangle;

    JButton exitToMainMenuButton;

    BufferedImage bearImage;

    UIDisplay(MainGamePanel mainGamePanel) throws IOException {
        this.mainGamePanel = mainGamePanel;
        arial_40B = new Font("Arial", Font.BOLD, 40);
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_20 = new Font("Arial", Font.PLAIN, 20);

        startButtonRectangle = new Rectangle((int) (500),
                (int) (mainGamePanel.panelHeight / 5),
                (int) (mainGamePanel.getTILE_SIZE() * 20),
                (int) (mainGamePanel.getTILE_SIZE() * 2));
        gameStartButton = new JButton();

        exitToMainMenuButton = new JButton();
        try {
            bearImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/misc/clipart.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d) {

        switch(mainGamePanel.gameState) {
            case startState:
                // dark gray background
                g2d.setColor(Color.DARK_GRAY);
                g2d.fillRect(0,
                        0,
                        (int)(mainGamePanel.panelWidth),
                        (int)(mainGamePanel.panelHeight));


                // display FPS
                g2d.setFont(arial_20);
                g2d.setColor(Color.lightGray);

                String text = "FPS: " + mainGamePanel.lastDrawCount;

                int x = mainGamePanel.getTILE_SIZE();
                int y = mainGamePanel.getTILE_SIZE();
                g2d.drawString(text, x, y);

                // game title
                g2d.setFont(arial_40B);
                String gameName = "Loom Bear Jack";
                int textLength = (int) g2d.getFontMetrics().getStringBounds(gameName, g2d).getWidth();
                int xGameTitle = mainGamePanel.panelWidth/2 - textLength/2;
                int yGameTitle = mainGamePanel.getTILE_SIZE();
                g2d.drawString(gameName, xGameTitle, yGameTitle);

                // start game panel
                g2d.fillRect((int)(textLength),
                        (int)(mainGamePanel.panelHeight/10),
                        (int)(mainGamePanel.panelWidth * 0.7),
                        (int)(mainGamePanel.panelHeight * 0.7));

                // start button
                g2d.setColor(Color.DARK_GRAY);
                g2d.fill(startButtonRectangle);

                g2d.setColor(Color.lightGray);
                g2d.setFont(arial_40);
                g2d.drawString("Start Game", (int)(startButtonRectangle.x*1.7), (int)(startButtonRectangle.y*1.28));

                g2d.drawImage(bearImage, (int)(startButtonRectangle.x*1.4),(int)(startButtonRectangle.y*1.8),null);

                break;

            case initialGameState:
                //rectangle for FPS and player HUD
                g2d.setColor(new Color(0,0,0,100));
                g2d.fillRect(2,2, (int) (mainGamePanel.TILE_SIZE * 2.5), mainGamePanel.TILE_SIZE * 3);

                // display FPS
                g2d.setFont(arial_20);
                g2d.setColor(new Color(227, 227, 227,150));

                text = "FPS: " + mainGamePanel.lastDrawCount;

                x = mainGamePanel.TILE_SIZE/5;
                y = mainGamePanel.TILE_SIZE/2;
                g2d.drawString(text, x, y);

                // display player HUD
                //hp
                g2d.setFont(arial_40);
                g2d.setColor(new Color(168, 168, 168));

                text = "HP: " + mainGamePanel.player.hp;
                x = (int)(mainGamePanel.TILE_SIZE*0.2);
                y = (int)(mainGamePanel.TILE_SIZE*1.5);
                g2d.drawString(text, x, y);

                // exit to main menu
                text = "Exit";
                x = mainGamePanel.panelWidth - (mainGamePanel.TILE_SIZE*2);
                y = mainGamePanel.TILE_SIZE;
                g2d.drawString(text, x, y);


                break;
        }
    }
}
