package tile;

import game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles1/grass01.png")));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles1/wall.png")));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles1/water01.png")));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gamePanel.getMaxScreenColumn() && row < gamePanel.getMaxScreenRow()) {
            g2.drawImage(tile[0].image,x,y, gamePanel.tileSize, gamePanel.tileSize, null);
            col++;
            x+= gamePanel.tileSize;

            if(col == gamePanel.getMaxScreenColumn()) {
                col = 0;
                x = 0;
                row++;
                y+= gamePanel.tileSize;
            }
        }
    }
}
