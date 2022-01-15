package object;

import game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectChest extends SuperObject {
    public ObjectChest() {
        name = "Chest";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }

//        solidArea.x = 0;
    }
}
