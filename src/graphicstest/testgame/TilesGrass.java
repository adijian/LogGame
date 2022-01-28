package graphicstest.testgame;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class TilesGrass extends Tiles{

    TilesGrass() throws IOException {
        this.collision = false;
        this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles1/grass.png")));
    }

}
