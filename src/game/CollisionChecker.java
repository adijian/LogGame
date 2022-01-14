package game;

import entity.Entity;

public class CollisionChecker {

    GamePanel gamePanel;


    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftColumn = entityLeftWorldX / gamePanel.getTileSize();
        int entityRightColumn = entityRightWorldX / gamePanel.getTileSize();
        int entityTopRow = entityTopWorldY / gamePanel.getTileSize();
        int entityBottomRow = entityBottomWorldY / gamePanel.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[entityRightColumn][entityTopRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].isCollision() || gamePanel.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[entityLeftColumn][entityBottomRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[entityRightColumn][entityBottomRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].isCollision() || gamePanel.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftColumn = (entityLeftWorldX - entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[entityLeftColumn][entityBottomRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].isCollision() || gamePanel.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightColumn = (entityRightWorldX + entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[entityRightColumn][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[entityRightColumn][entityBottomRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].isCollision() || gamePanel.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }
}
