package graphicstest.testgame;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TilesManager {

    GamePanel gamePanel;
    Tiles[] tile;
    int[][] mapTileNum;

    TilesManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tiles[10]; // regards to the number of tile types available
        mapTileNum = new int[gamePanel.maxTilesScreenColumn][gamePanel.maxTilesScreenColumn];
        getTilesImages();
        loadMap("/maptiles/tileMapGame2.txt");
    }

    void getTilesImages() {
        try {
            tile[0] = new TilesGrass();
        } catch(IOException o) {
            o.printStackTrace();
        }
    }

    void loadMap(String filePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            assert inputStream != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int column = 0;
            int row = 0;

            while(column < gamePanel.maxTilesScreenColumn && row < gamePanel.maxTilesScreenRow) {
                String line = bufferedReader.readLine();

                while(column < gamePanel.maxTilesScreenColumn) {
                    String[] numbersInLine = line.split(" ");
                    int numberInCell = Integer.parseInt(numbersInLine[column]);

                    mapTileNum[column][row] = numberInCell;
                    column++;
                }

                if (column == gamePanel.maxTilesScreenColumn) {
                    column = 0;
                    row++;
                }
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void draw(Graphics2D g2d) {
        int worldColumn = 0;
        int worldRow = 0;

        while(worldColumn < gamePanel.maxTilesScreenColumn && worldRow < gamePanel.maxTilesScreenRow){
            int tileNum = mapTileNum[worldColumn][worldRow];
            int worldX = worldColumn * gamePanel.TILE_SIZE;
            int worldY = worldRow * gamePanel.TILE_SIZE;

            // TODO changed by player
//            int screenX = worldX - gamePanel

            g2d.drawImage(tile[tileNum].image, worldX, worldY, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);

            worldColumn++;
            if(worldColumn == gamePanel.maxTilesScreenColumn) {
                worldColumn = 0;
                worldRow++;
            }

        }

    }
}
