package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectTree extends SuperObject{
    public ObjectTree() {
        name = "Tree";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/tree.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
