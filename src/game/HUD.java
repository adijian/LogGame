package game;
import java.awt.*;

public class HUD {
    GamePanel gamePanel;
    Color brownish;
    Font arial_10B;

    public HUD(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        brownish = new Color(99, 48, 17, 183);
        arial_10B = new Font("Arial", Font.BOLD, 10);
    }

    public void draw(Graphics2D g2) {
        g2.setColor(brownish);
        g2.fillRect(1615,600,300,500);
        g2.setColor(Color.white);
        g2.fillRect(1630,615,100,75);
        g2.setColor(Color.black);
        g2.setFont(arial_10B);
        g2.drawString("Woodcutting xp: ", 1635,630);
        g2.drawString("" + gamePanel.getPlayer().getWoodcuttingXP(), 1635,650);
        g2.drawString("Level: " + gamePanel.getPlayer().LevelChecker(gamePanel.getPlayer().getWoodcuttingXP(), gamePanel.getPlayer().getWoodcuttingLevel()), 1635,670);
        }
}
