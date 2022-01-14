package tile;

import game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[gamePanel.getMaxWorldColumn()][gamePanel.getMaxWorldRow()];
        getTileImage();
        loadMap("/maptiles/tileMap2.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles1/grass.png")));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles1/wall.png")));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles1/water.png")));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles1/earth.png")));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles1/tree.png")));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles1/sand.png")));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filepath){
        try{
            InputStream inputStream = getClass().getResourceAsStream(filepath);
            assert inputStream != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while(col < gamePanel.getMaxWorldColumn() && row < gamePanel.getMaxWorldRow()) {
                String line = bufferedReader.readLine();

                while(col< gamePanel.getMaxWorldColumn()) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanel.getMaxWorldColumn()) {
                    col = 0;
                    row ++;
                }
            }
            bufferedReader.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldColumn = 0;
        int worldRow = 0;

        while(worldColumn < gamePanel.getMaxWorldColumn() && worldRow < gamePanel.getMaxWorldRow()) {

            int tileNum = mapTileNum[worldColumn][worldRow];

            int worldX = worldColumn * gamePanel.getTileSize();
            int worldY = worldRow * gamePanel.getTileSize();
            int screenX = worldX - gamePanel.getPlayer().worldX + gamePanel.getPlayer().screenX;
            int screenY = worldY - gamePanel.getPlayer().worldY + gamePanel.getPlayer().screenY;

            if (worldX + gamePanel.getTileSize() > gamePanel.getPlayer().worldX - gamePanel.getPlayer().screenX &&
                    worldX - gamePanel.getTileSize() < gamePanel.getPlayer().worldX + gamePanel.getPlayer().screenX &&
                    worldY + gamePanel.getTileSize() > gamePanel.getPlayer().worldY - gamePanel.getPlayer().screenY &&
                    worldY - gamePanel.getTileSize() < gamePanel.getPlayer().worldY + gamePanel.getPlayer().screenY)
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            }

            worldColumn++;

            if(worldColumn == gamePanel.getMaxScreenColumn()) {
                worldColumn = 0;
                worldRow++;
            }
        }
    }
}
