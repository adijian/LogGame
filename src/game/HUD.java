package game;
import java.awt.*;

public class HUD {
    GamePanel gamePanel;
    Color brownish;
    Font arial_20;

    public HUD(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        brownish = new Color(99, 48, 17, 183);
        arial_20 = new Font("Arial", Font.PLAIN, 20);
    }

    public void draw(Graphics2D g2) {
        g2.setColor(brownish);
        g2.fillRect(1615,600,300,500);
        g2.setColor(Color.white);
        g2.setFont(arial_20);
        g2.drawString("Woodcutting xp: " + String.valueOf(gamePanel.getPlayer().getWoodcuttingXP()), 1630,650);
    }
}
