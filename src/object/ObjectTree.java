package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectTree extends SuperObject{
    public ObjectTree() {
        name = "Tree";
        hp = defaultTreeHp;
        collision = true;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/tree2.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }

        try {
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles1/grass.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
