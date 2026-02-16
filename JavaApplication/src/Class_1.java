import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Class_1 extends JFrame {

    private static Class_1 game_game;
    private static Image logoImage;
    private static Image decorImage;
    private static float logoX = 50;
    private static float logoY = 50;
    private static float targetX;
    private static float targetY;
    private static float speed = 2;
    private static long last_frame_time;

    public static void main(String[] args) throws IOException {
        logoImage = ImageIO.read(Class_1.class.getResourceAsStream("/logo.png"));
        decorImage = ImageIO.read(Class_1.class.getResourceAsStream("/decor.png"));

        game_game = new Class_1();
        game_game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_game.setLocation(200, 50);
        game_game.setSize(600, 400);
        game_game.setResizable(false);

        // Рассчитываем центр с учётом размера логотипа
        targetX = (600 - logoImage.getWidth(null)) / 2;
        targetY = (400 - logoImage.getHeight(null)) / 3;

        GameField game_field = new GameField();
        game_game.add(game_field);
        game_game.setVisible(true);
        last_frame_time = System.nanoTime();
    }

    private static void onRepaint(Graphics g) {
    long current_time = System.nanoTime();
    float delta_time = (current_time - last_frame_time) * 0.000000001f;
    last_frame_time = current_time;

    int smallWidth = logoImage.getWidth(null) / 2;
    int smallHeight = logoImage.getHeight(null) / 2;
    g.drawImage(logoImage, 600 - smallWidth - 10, 10, smallWidth, smallHeight, null);
    
    int decorX = (600 - decorImage.getWidth(null)) / 2;
    int decorY = (400 - decorImage.getHeight(null)) / 2;
    g.drawImage(decorImage, decorX, decorY, null);
}

    private static class GameField extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            onRepaint(g);
            repaint();
        }
    }
}