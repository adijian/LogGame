package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectTree extends SuperObject{
    public ObjectTree() {
        name = "Tree";
        hp = defaultTreeHp;
        hitsTaken = 0;
        treeResetTimer = 0;
        collision = true;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/tree2.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
