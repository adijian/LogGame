package game;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenColumn = 40;
    final int maxScreenRow = 23;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;
    int drawCount = 0;

    final int maxWorldColumn = 50;
    final int maxWorldRow = 50;

    Thread gameThread;
    CollisionChecker collisionChecker = new CollisionChecker(this);
    KeyHandler keyHandler = new KeyHandler();
    SuperObject[] object = new SuperObject[5];
    AssetSetter assetSetter = new AssetSetter(this);
    UI ui = new UI(this);
    HUD hud = new HUD(this);

    int FPS = 144;

    public Player player = new Player(this, keyHandler);

    TileManager tileManager = new TileManager(this);

    GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        // Call objects
//        assetSetter.setObject();
        assetSetter.setTrees();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // draw tiles
        tileManager.draw(g2);

        // draw objects
        for(int i = 0;i < object.length;i++) {
            if(object[i] != null) {
                // if tree is down tick tree reset timer
                if(object[i].isTreeDown() && object[i].getTreeResetTimer() > 0) {
                    object[i].setTreeResetTimer(object[i].getTreeResetTimer() - 1);
                }

                if(object[i].isTreeDown() && object[i].getTreeResetTimer() == 0) {
                    object[i].setTreeDown(false);
                    object[i].setTreeResetTimer(object[i].getTreeDefaultDownResetTimer());
                    object[i].setHp(object[i].getDefaultTreeHp());
                    try {
                        object[i].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/tree2.png"))));
                        System.out.println(i + " is down");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                object[i].draw(g2, this);
            }
        }

        // draw player
        player.draw(g2);

        // draw ui
        ui.draw(g2);

        // draw HUD
        hud.draw(g2);

        //delete all
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
                try {
                    update();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                ui.setFps(drawCount);
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

    public void update() throws IOException {
        player.update();
    }

    public UI getUi() {
        return ui;
    }

    public int getDrawCount() {
        return drawCount;
    }
}
