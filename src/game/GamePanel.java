package game;

import entity.Player;
import object.SuperObject;
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
    int drawCount = 0;

    final int maxWorldColumn = 50;
    final int maxWorldRow = 50;
    final int worldWidth = tileSize * maxScreenColumn;
    final int worldHeight = tileSize * maxScreenRow;

    Thread gameThread;
    CollisionChecker collisionChecker = new CollisionChecker(this);
    KeyHandler keyHandler = new KeyHandler();
    SuperObject[] object = new SuperObject[30];
    AssetSetter assetSetter = new AssetSetter(this);
    UI ui = new UI(this);

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

    public void setupGame() {
        // Call objects
        assetSetter.setObject();
        assetSetter.setTrees();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        for(int i = 0;i< getObject().length;i++) {
            if(getObject()[i] != null) {
                getObject()[i].draw(g2, this);
            }
        }

        if(drawCount> 140) {
            ui.showFPS(drawCount, g2);
        } else {ui.showFPS(144, g2);}

        player.draw(g2);
        ui.draw(g2);
        g2.dispose();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000d / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
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
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public SuperObject[] getObject() {
        return object;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public TileManager getTileManager() {
        return tileManager;
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

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public Player getPlayer() {
        return player;
    }

    public void update(){
        player.update();
    }

    public UI getUi() {
        return ui;
    }

    public int getDrawCount() {
        return drawCount;
    }
}
