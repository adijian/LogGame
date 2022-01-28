package graphicstest.testgame;

public class CollisionCheck {

    MainGamePanel gamePanel;


    public CollisionCheck(MainGamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entitys entity) {
        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftColumn = entityLeftWorldX / gamePanel.TILE_SIZE;
        int entityRightColumn = entityRightWorldX / gamePanel.TILE_SIZE;
        int entityTopRow = entityTopWorldY / gamePanel.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / gamePanel.TILE_SIZE;

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gamePanel.TILE_SIZE;
                tileNum1 = gamePanel.tilesManager.mapTileNum[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.tilesManager.mapTileNum[entityRightColumn][entityTopRow];
                if (gamePanel.tilesManager.tile[tileNum1].isCollision() || gamePanel.tilesManager.tile[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gamePanel.TILE_SIZE;
                tileNum1 = gamePanel.tilesManager.mapTileNum[entityLeftColumn][entityBottomRow];
                tileNum2 = gamePanel.tilesManager.mapTileNum[entityRightColumn][entityBottomRow];
                if (gamePanel.tilesManager.tile[tileNum1].isCollision() || gamePanel.tilesManager.tile[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftColumn = (entityLeftWorldX - entity.getSpeed()) / gamePanel.TILE_SIZE;
                tileNum1 = gamePanel.tilesManager.mapTileNum[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.tilesManager.mapTileNum[entityLeftColumn][entityBottomRow];
                if (gamePanel.tilesManager.tile[tileNum1].isCollision() || gamePanel.tilesManager.tile[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightColumn = (entityRightWorldX + entity.getSpeed()) / gamePanel.TILE_SIZE;
                tileNum1 = gamePanel.tilesManager.mapTileNum[entityRightColumn][entityTopRow];
                tileNum2 = gamePanel.tilesManager.mapTileNum[entityRightColumn][entityBottomRow];
                if (gamePanel.tilesManager.tile[tileNum1].isCollision() || gamePanel.tilesManager.tile[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }

//    public int checkObject(Entity entity, boolean player) {
//        int index = 999;
//
//        for(int i = 0; i<gamePanel.getObject().length; i++) {
//            if (gamePanel.getObject()[i] != null) {
//                //Get entity's solid area position
//                entity.solidArea.x = entity.worldX + entity.solidArea.x;
//                entity.solidArea.y = entity.worldY + entity.solidArea.y;
//
//                //Get the object's solid area position
//                gamePanel.getObject()[i].solidArea.x = gamePanel.getObject()[i].worldX + gamePanel.getObject()[i].solidArea.x;
//                gamePanel.getObject()[i].solidArea.y = gamePanel.getObject()[i].worldY + gamePanel.getObject()[i].solidArea.y;
//
//                switch (entity.getDirection()) {
//                    case "up":
//                        entity.solidArea.y -= entity.speed;
//                        if (entity.solidArea.intersects(gamePanel.getObject()[i].solidArea)) {
//                            if(gamePanel.getObject()[i].isCollision()) {
//                                entity.setCollisionOn(true);
//                            }
//                            if(player) {
//                                index = i;
//                                //if it's player, return the index
//                            }
//                        }
//                        break;
//                    case "down":
//                        entity.solidArea.y += entity.speed;
//                        if (entity.solidArea.intersects(gamePanel.getObject()[i].solidArea)) {
//                            if(gamePanel.getObject()[i].isCollision()) {
//                                entity.setCollisionOn(true);
//                            }
//                            if(player) {
//                                index = i;
//                                //if it's player, return the index
//                            }
//                        }
//                        break;
//                    case "left":
//                        entity.solidArea.x -= entity.speed;
//                        if (entity.solidArea.intersects(gamePanel.getObject()[i].solidArea)) {
//                            if(gamePanel.getObject()[i].isCollision()) {
//                                entity.setCollisionOn(true);
//                            }
//                            if(player) {
//                                index = i;
//                                //if it's player, return the index
//                            }
//                        }
//                        break;
//                    case "right":
//                        entity.solidArea.x += entity.speed;
//                        if (entity.solidArea.intersects(gamePanel.getObject()[i].solidArea)) {
//                            if(gamePanel.getObject()[i].isCollision()) {
//                                entity.setCollisionOn(true);
//                            }
//                            if(player) {
//                                index = i;
//                                //if it's player, return the index
//                            }
//                        }
//                        break;
//                }
//                entity.solidArea.x = entity.getSolidAreaDefaultX();
//                entity.solidArea.y = entity.getSolidAreaDefaultY();
//                gamePanel.getObject()[i].solidArea.x = gamePanel.getObject()[i].getSolidAreaDefaultX();
//                gamePanel.getObject()[i].solidArea.y = gamePanel.getObject()[i].getSolidAreaDefaultY();
//            }
//        }
//        return index;
//    }
}
