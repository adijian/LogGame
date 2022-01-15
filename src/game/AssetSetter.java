package game;

import object.ObjectChest;
import object.ObjectDoor;
import object.ObjectKey;

public class AssetSetter {

    GamePanel gamePanel;

    AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.getObject()[0] = new ObjectKey();
        gamePanel.getObject()[0].setWorldX(23 * gamePanel.getTileSize());
        gamePanel.getObject()[0].setWorldY(7 * gamePanel.getTileSize());

        gamePanel.getObject()[1] = new ObjectKey();
        gamePanel.getObject()[1].setWorldX(23 * gamePanel.getTileSize());
        gamePanel.getObject()[1].setWorldY(40 * gamePanel.getTileSize());

        gamePanel.getObject()[2] = new ObjectDoor();
        gamePanel.getObject()[2].setWorldX(37 * gamePanel.getTileSize());
        gamePanel.getObject()[2].setWorldY(7 * gamePanel.getTileSize());

        gamePanel.getObject()[3] = new ObjectDoor();
        gamePanel.getObject()[3].setWorldX(10 * gamePanel.getTileSize());
        gamePanel.getObject()[3].setWorldY(11 * gamePanel.getTileSize());

        gamePanel.getObject()[4] = new ObjectDoor();
        gamePanel.getObject()[4].setWorldX(8 * gamePanel.getTileSize());
        gamePanel.getObject()[4].setWorldY(28 * gamePanel.getTileSize());

        gamePanel.getObject()[5] = new ObjectDoor();
        gamePanel.getObject()[5].setWorldX(12 * gamePanel.getTileSize());
        gamePanel.getObject()[5].setWorldY(22 * gamePanel.getTileSize());

        gamePanel.getObject()[6] = new ObjectChest();
        gamePanel.getObject()[6].setWorldX(10 * gamePanel.getTileSize());
        gamePanel.getObject()[6].setWorldY(7 * gamePanel.getTileSize());
    }
}
