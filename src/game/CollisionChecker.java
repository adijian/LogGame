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

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for(int i = 0; i<gamePanel.getObject().length; i++) {
            if (gamePanel.getObject()[i] != null) {
                //Get entity's solid area position
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;

                //Get the object's solid area position
                gamePanel.getObject()[i].getSolidArea().x = gamePanel.getObject()[i].getWorldX() + gamePanel.getObject()[i].getSolidArea().x;
                gamePanel.getObject()[i].getSolidArea().y = gamePanel.getObject()[i].getWorldY() + gamePanel.getObject()[i].getSolidArea().y;

                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(gamePanel.getObject()[i].getSolidArea())) {
                            System.out.println("up collision");
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if (entity.getSolidArea().intersects(gamePanel.getObject()[i].getSolidArea())) {
                            System.out.println("down collision");
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(gamePanel.getObject()[i].getSolidArea())) {
                            System.out.println("left collision");
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if (entity.getSolidArea().intersects(gamePanel.getObject()[i].getSolidArea())) {
                            System.out.println("right collision");
                            break;
                        }
                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                gamePanel.getObject()[i].getSolidArea().x = gamePanel.getObject()[i].getSolidAreaDefaultX();
                gamePanel.getObject()[i].getSolidArea().y = gamePanel.getObject()[i].getSolidAreaDefaultY();
            }
        }
        return index;
    }
}
