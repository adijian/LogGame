package object;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

    public class ObjectBoots extends SuperObject {
        public ObjectBoots() {
            name = "Boots";
            try {
                image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png")));
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
}
