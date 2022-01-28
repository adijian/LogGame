package graphicstest.testgame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    int worldX, worldY;
    int speed;
    BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    String direction;
    int spriteCounter = 0;
    int spriteNum = 1;
    Rectangle solidArea;
    boolean collisionOn = false;
    int solidAreaDefaultX, solidAreaDefaultY;

}
