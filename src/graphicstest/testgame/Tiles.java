package graphicstest.testgame;

import java.awt.image.BufferedImage;

abstract class Tiles {

    BufferedImage image;
    boolean collision = false;

    public boolean isCollision() {
        return collision;
    }
}
