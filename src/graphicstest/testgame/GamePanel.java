package graphicstest.testgame;

import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

class GamePanel extends JPanel implements Runnable {
    // tile size modifiers
    final int BASE_TILE_SIZE = 16;
    int scale = 3;
    final int TILE_SIZE = BASE_TILE_SIZE * scale;

    int panelWidth = 1920;
    int panelHeight = 1080;

    int minTilesScreenColumn = (int) Math.ceil((double) panelWidth / TILE_SIZE);
    int minTilesScreenRow = (int) Math.ceil((double)panelHeight / TILE_SIZE);

    int worldMultiplier = 1;
    int maxTilesScreenColumn = worldMultiplier * minTilesScreenColumn;
    int maxTilesScreenRow = worldMultiplier * minTilesScreenRow;

    // gameloop params
    int FPS = 144;
    int drawCount = 0;
    int lastDrawCount = 1;
    Thread gameThread;

    UIDisplay ui = new UIDisplay(this);

    //game state
    enumGameState gameState;

    TilesManager tilesManager = new TilesManager(this);

    GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setDoubleBuffered(true);
//        this.addKeyListener(keyHandler);
        this.setFocusable(true);


        //TODO DEBUG
        System.out.println("minTilesScreenColumn: " + minTilesScreenColumn);
        System.out.println("minTilesScreenRow: " + minTilesScreenRow);

        System.out.println("maxTilesScreenColumn: " + maxTilesScreenColumn);
        System.out.println("maxTilesScreenRow: " + maxTilesScreenRow);

        gameState = enumGameState.startState;
        startGameThread();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        switch(gameState) {
            case startState:
                ui.gameStartButton.setBounds(ui.startButtonRectangle);
                ui.gameStartButton.setContentAreaFilled(false);
                ui.gameStartButton.setBorderPainted(false);
                ui.gameStartButton.setOpaque(false);
                ui.gameStartButton.addActionListener(e -> gameState = enumGameState.initialGameState);
                this.add(ui.gameStartButton);

                break;

            case initialGameState:
                tilesManager.draw(g2d);

                ui.exitToMainMenuButton.setBounds(panelWidth - (TILE_SIZE*2), 0, 500,500);
                ui.exitToMainMenuButton.setContentAreaFilled(false);
                ui.exitToMainMenuButton.setBorderPainted(false);
                ui.exitToMainMenuButton.setOpaque(false);
                ui.exitToMainMenuButton.addActionListener(e -> gameState = enumGameState.startState);
                this.add(ui.exitToMainMenuButton);

                break;
        }
        ui.draw(g2d);


        g2d.dispose();

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
                if (lastDrawCount != drawCount) {
                    lastDrawCount = drawCount;
                    //TODO DEBUG
                    System.out.println(lastDrawCount);
                }
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() throws IOException {


    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public int getTILE_SIZE() {
        return TILE_SIZE;
    }
}