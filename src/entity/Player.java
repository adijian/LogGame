package entity;

import game.GamePanel;
import game.KeyHandler;
import game.Levels;

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
    int originalHitsDelay = 100; //300 can be changed to debug
    int hitsDelay;
    int treesCollected = 0;
    float woodcuttingXP;
    int woodcuttingLevel = 1;

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
        hitsDelay = originalHitsDelay;

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
                    if(!gamePanel.getObject()[i].isTreeDown() && hitsDelay > 0) {
                        hitsDelay--;
                        // if collides and tree isn't down, show message cutting tree
                        if(!gamePanel.getObject()[i].isTreeDown()) {
                            gamePanel.getUi().showMessage("Cutting tree...");
                        }
                    }
                    // if tree hp lower or equal than default hp and hits delay is lower or equal than 0,
                    // raise hits taken and lower hp. reset hits delay.

                    // keep hitting tree until its hp is 0
                    if (gamePanel.getObject()[i].getHp() > 0 && hitsDelay == 0) {
                        gamePanel.getObject()[i].setHp(gamePanel.getObject()[i].getHp() - 1);
                        hitsDelay = originalHitsDelay;
                    }
                    // if tree hp lower or equal than 0 and tree is not down,
                    // set tree down equals true, set image to no tree, show message tree down and raise stats

                    //if tree is down, disappear
                    if (gamePanel.getObject()[i].getHp() == 0 && !gamePanel.getObject()[i].isTreeDown()){
                        gamePanel.getObject()[i].setTreeDown(true);
                        gamePanel.getObject()[i].setImage(gamePanel.getObject()[i].getImage2());
                        gamePanel.getUi().showMessage("Tree down!");
                        gamePanel.getPlayer().setTreesCollected(gamePanel.getPlayer().getTreesCollected() + 1);
                        gamePanel.getPlayer().setWoodcuttingXP(gamePanel.getPlayer().getWoodcuttingXP() + 25);
                    }
                    break;
            }
        }
    }

    public int LevelChecker(float xp, int level) {
        if(xp > 0 && xp < Levels.level1.rank) {
            level = 1;
        }
        if(xp > Levels.level1.rank && xp < Levels.level2.rank) {
            level = 2;
        }
        if(xp > Levels.level2.rank && xp < Levels.level3.rank) {
            level = 3;
        }
        if(xp > Levels.level3.rank && xp < Levels.level4.rank) {
            level = 4;
        }
        if(xp > Levels.level4.rank && xp < Levels.level5.rank) {
            level = 5;
        }
        if(xp > Levels.level5.rank && xp < Levels.level6.rank) {
            level = 6;
        }
        if(xp > Levels.level6.rank && xp < Levels.level7.rank) {
            level = 7;
        }
        if(xp > Levels.level7.rank && xp < Levels.level8.rank) {
            level = 8;
        }
        if(xp > Levels.level8.rank && xp < Levels.level9.rank) {
            level = 9;
        }
        if(xp > Levels.level9.rank && xp < Levels.level10.rank) {
            level = 10;
        }
        if(xp > Levels.level10.rank && xp < Levels.level11.rank) {
            level = 11;
        }
        if(xp > Levels.level11.rank && xp < Levels.level12.rank) {
            level = 12;
        }
        if(xp > Levels.level12.rank && xp < Levels.level13.rank) {
            level = 13;
        }
        if(xp > Levels.level13.rank && xp < Levels.level14.rank) {
            level = 14;
        }
        if(xp > Levels.level14.rank && xp < Levels.level15.rank) {
            level = 15;
        }
        if(xp > Levels.level15.rank && xp < Levels.level16.rank) {
            level = 16;
        }
        if(xp > Levels.level16.rank && xp < Levels.level17.rank) {
            level = 17;
        }
        if(xp > Levels.level17.rank && xp < Levels.level18.rank) {
            level = 18;
        }
        if(xp > Levels.level18.rank && xp < Levels.level19.rank) {
            level = 19;
        }
        if(xp > Levels.level19.rank && xp < Levels.level20.rank) {
            level = 20;
        }
        return level;
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

    public float getWoodcuttingXP() {
        return woodcuttingXP;
    }

    public void setWoodcuttingXP(float woodcuttingXP) {
        this.woodcuttingXP = woodcuttingXP;
    }

    public int getWoodcuttingLevel() {
        return woodcuttingLevel;
    }

    public void setWoodcuttingLevel(int woodcuttingLevel) {
        this.woodcuttingLevel = woodcuttingLevel;
    }
}
