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
    int hasKey = 0;
    int hitsDelay = 300;
    int treesCollected = 0;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.getScreenWidth()/2 - (gamePanel.getTileSize()/2);
        screenY = gamePanel.getScreenHeight()/2 - (gamePanel.getTileSize()/2);

        solidArea = new Rectangle();
        solidArea.x = 6;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 28;
        solidArea.height = 28;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {
        worldX = gamePanel.getTileSize() * 23; // starting positions
        worldY = gamePanel.getTileSize() * 21;
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

    public void update() throws IOException {

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

            // Check object collision
            int objectIndex = gamePanel.getCollisionChecker().checkObject(this, true);
            pickUpObject(objectIndex);

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

    public void pickUpObject(int i) throws IOException {
        if(i != 999) {
            String objectName = gamePanel.getObject()[i].getName();

            switch (objectName) {
                case "Key":
                    hasKey++;
                    gamePanel.getObject()[i] = null;
                    gamePanel.getUi().showMessage("You got a key!");
                    break;

                case "Door":
                    if(hasKey > 0) {
                        gamePanel.getObject()[i] = null;
                        hasKey--;
                        gamePanel.getUi().showMessage("You opened the door!");
                    }
                    else {
                        gamePanel.getUi().showMessage("You need a key!");
                    }
                    break;

                case "Boots":
                    speed += 1;
                    gamePanel.getObject()[i] = null;
                    gamePanel.getUi().showMessage("You got a boost!");
                    break;

                case "Chest":
                    gamePanel.getUi().setGameFinished(true);
                    break;

                case "Tree" :
                    if(hitsDelay > 0) {
                        hitsDelay--;
                        gamePanel.getUi().showMessage("Cutting tree...");
                    }
                    if (gamePanel.getObject()[i].getHp() <= 3 && hitsDelay <= 0) {
                        gamePanel.getObject()[i].setHitsTaken(gamePanel.getObject()[i].getHitsTaken() + 1);
                        gamePanel.getObject()[i].setHp(gamePanel.getObject()[i].getHp() - gamePanel.getObject()[i].getHitsTaken());
                        hitsDelay = 20;
                    }
                    if (gamePanel.getObject()[i].getHp() <= 0){
                        gamePanel.getObject()[i].setHitsTaken(0);
                        gamePanel.getObject()[i].setTreeResetTimer(300);
                        gamePanel.getObject()[i].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles1/grass.png"))));
//                        gamePanel.getObject()[i] = null;
                        gamePanel.getUi().showMessage("Tree down!");
                        if(!gamePanel.getObject()[i].isTreeDown()) {
                            gamePanel.getPlayer().setTreesCollected(gamePanel.getPlayer().getTreesCollected() + 1);
                        }
                        gamePanel.getObject()[i].setTreeDown(true);
                    }
                    break;
            }
        }
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getHasKey() {
        return hasKey;
    }

    public int getTreesCollected() {
        return treesCollected;
    }

    public void setTreesCollected(int treesCollected) {
        this.treesCollected = treesCollected;
    }
}
