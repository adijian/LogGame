package game;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;

    final int maxWorldColumn = 50;
    final int maxWorldRow = 50;
    final int worldWidth = tileSize * maxScreenColumn;
    final int worldHeight = tileSize * maxScreenColumn;


    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    int FPS = 144;

    public Player player = new Player(this, keyHandler);

    TileManager tileManager = new TileManager(this);

    GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getMaxWorldColumn() {
        return maxWorldColumn;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenColumn() {
        return maxScreenColumn;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public Player getPlayer() {
        return player;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000d / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        int drawCount = 0;
        int timer = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
}
