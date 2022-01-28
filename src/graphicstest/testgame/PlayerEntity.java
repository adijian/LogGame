package graphicstest.testgame;

import game.GamePanel;
import game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

class PlayerEntity extends Entitys{

    MainGamePanel gamePanel;
    KeysHandler keysHandler;

    final int screenX;
    final int screenY;

    int woodcuttingLevel;

//    int hitsDelay;
//    int originalHitsDelay;

    PlayerEntity(MainGamePanel gamePanel, KeysHandler keysHandler) {
        this.gamePanel = gamePanel;
        this.keysHandler = keysHandler;

        this.direction = "down";
        this.speed = 2;
        int hp = 3;

        this.worldX = gamePanel.TILE_SIZE * 25; // starting positions
        this.worldY = gamePanel.TILE_SIZE * 25; // starting positions

        screenX = gamePanel.panelWidth / 2 - (gamePanel.TILE_SIZE / 2);
        screenY = gamePanel.panelHeight / 2 - (gamePanel.TILE_SIZE / 2);
        solidArea = new Rectangle();

        solidArea.x = 6;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 28;
        solidArea.height = 28;
        woodcuttingLevel = 0;

//        this.hitsDelay = originalHitsDelay;

        //Player image
        try{
            this.up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            this.up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            this.down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            this.down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            this.left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            this.left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            this.right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            this.right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() throws IOException {

        if(keysHandler.isUpPressed() ||
           keysHandler.isDownPressed() ||
           keysHandler.isLeftPressed() ||
           keysHandler.isRightPressed()) {

            if(keysHandler.isUpPressed()) {
                direction = "up";
            }
            else if(keysHandler.isDownPressed()) {
                direction = "down";
            }
            else if(keysHandler.isLeftPressed()) {
                direction = "left";
            }
            else if(keysHandler.isRightPressed()) {
                direction = "right";
            }
            // Check tile collision
            collisionOn = false;
            gamePanel.collisionCheck.checkTile(this);

            // Check object collision
//            int objectIndex = gamePanel.collisionCheck.checkObject(this, true);
//            pickUpObject(objectIndex);

            // If collision is false, player can move
            if(!collisionOn) {
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
        g2.drawImage(image, screenX, screenY, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
    }

}
