package graphicstest.testgame;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TilesManager {

    MainGamePanel mainGamePanel;
    Tiles[] tile;
    int[][] mapTileNum;

    TilesManager(MainGamePanel mainGamePanel, String tileMapFilePath) {
        this.mainGamePanel = mainGamePanel;
        tile = new Tiles[10]; // regards to the number of tile types available
        mapTileNum = new int[mainGamePanel.maxTilesScreenColumn][mainGamePanel.maxTilesScreenColumn];
        getTilesImages();
        loadMap(tileMapFilePath);
    }

    void getTilesImages() {
        try {
            tile[0] = new TilesGrass();
            tile[1] = new TilesTree();
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

            while(column < mainGamePanel.maxTilesScreenColumn && row < mainGamePanel.maxTilesScreenRow) {
                String line = bufferedReader.readLine();

                while(column < mainGamePanel.maxTilesScreenColumn) {
                    String[] numbersInLine = line.split(" ");
                    int numberInCell = Integer.parseInt(numbersInLine[column]);

                    mapTileNum[column][row] = numberInCell;
                    column++;
                }

                if (column == mainGamePanel.maxTilesScreenColumn) {
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

        while(worldColumn < mainGamePanel.maxTilesScreenColumn && worldRow < mainGamePanel.maxTilesScreenRow){
            int tileNum = mapTileNum[worldColumn][worldRow];
            int worldX = worldColumn * mainGamePanel.TILE_SIZE;
            int worldY = worldRow * mainGamePanel.TILE_SIZE;

            // TODO changed by player
//            int screenX = worldX - gamePanel

            g2d.drawImage(tile[tileNum].image, worldX, worldY, mainGamePanel.TILE_SIZE, mainGamePanel.TILE_SIZE, null);

            worldColumn++;
            if(worldColumn == mainGamePanel.maxTilesScreenColumn) {
                worldColumn = 0;
                worldRow++;
            }

        }

    }
}
