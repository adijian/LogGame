package object;

import game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    BufferedImage image;
    BufferedImage image2;
    String name;
    boolean collision = false;
    int worldX, worldY;
    Rectangle solidArea = new Rectangle(0,0,48,48);
    int solidAreaDefaultX = 0;
    int solidAreaDefaultY = 0;
    int hp;
    int defaultTreeHp = 5;
    int hitsTaken;
    int treeResetTimer;
    int treeDownResetTimer = 600;
    boolean treeDown = false;

    public void draw(Graphics2D g2, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
        int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

        if (worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
                worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() &&
                worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() &&
                worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY())
        {
            g2.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public boolean isCollision() {
        return collision;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHitsTaken() {
        return hitsTaken;
    }

    public void setHitsTaken(int hitsTaken) {
        this.hitsTaken = hitsTaken;
    }
    public void setTreeResetTimer(int timer) {
        this.treeResetTimer = timer;
    }

    public int getTreeResetTimer() {
        return treeResetTimer;
    }

    public void setTreeDown(boolean treeDown) {
        this.treeDown = treeDown;
    }

    public boolean isTreeDown() {
        return treeDown;
    }

    public int getDefaultTreeHp() {
        return defaultTreeHp;
    }

    public int treeDownResetTimer() {
        return this.treeDownResetTimer;
    }

    public BufferedImage getImage2() {
        return image2;
    }
}
