package graphicstest.testgame;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class TilesTree extends Tiles{

    TilesTree() throws IOException {
        this.collision = true;
        this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles1/tree.png")));
    }

}
