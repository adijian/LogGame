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
    int mapTileNum[][];

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[gamePanel.getMaxScreenColumn()][gamePanel.getMaxScreenRow()];
        getTileImage();
        loadMap();
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

    public void loadMap(){
        try{
            InputStream inputStream = getClass().getResourceAsStream("/maptiles/tileMap.txt");
            assert inputStream != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while(col < gamePanel.getMaxScreenColumn() && row < gamePanel.getMaxScreenRow()) {
                String line = bufferedReader.readLine();

                while(col< gamePanel.getMaxScreenColumn()) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanel.getMaxScreenColumn()) {
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
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gamePanel.getMaxScreenColumn() && row < gamePanel.getMaxScreenRow()) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image,x,y, gamePanel.tileSize, gamePanel.tileSize, null);
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
