package tile;

import java.awt.image.BufferedImage;

public class Tile {
    BufferedImage image;
    boolean collision = false;

    public boolean isCollision() {
        return collision;
    }
}
