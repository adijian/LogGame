package entity;

import game.GamePanel;
import game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;

    final int screenX;
    final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.getScreenWidth()/2 - (gamePanel.getTileSize()/2);
        screenY = gamePanel.getScreenHeight()/2 - (gamePanel.getTileSize()/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {
        worldX = gamePanel.getTileSize() * 10; // starting positions
        worldY = gamePanel.getTileSize() * 10;
        speed = 2;
        direction = "down";
    }

    public void getPlayerImage() {
        try{
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){

        if(keyHandler.isUpPressed() || keyHandler.isDownPressed() || keyHandler.isLeftPressed() || keyHandler.isRightPressed()) {
            if(keyHandler.isUpPressed()) {
                direction = "up";
            }
            else if(keyHandler.isDownPressed()) {
                direction = "down";
            }
            else if(keyHandler.isLeftPressed()) {
                direction = "left";
            }
            else if(keyHandler.isRightPressed()) {
                direction = "right";
            }
            // Check tile collision
            collisionOn = false;
            gamePanel.getCollisionChecker().checkTile(this);

            // If collision is false, player can move
            if(!isCollisionOn()) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            // If collision is true, player can move


            spriteCounter++;
            if(spriteCounter>12) { // player image refreshes every 12 frames
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }


}
