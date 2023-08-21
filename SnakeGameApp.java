
import javax.swing.JFrame;

public class SnakeGameApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        SnakeGamePanel gamePanel = new SnakeGamePanel();
        frame.add(gamePanel);
        
        frame.pack();  
        frame.setVisible(true);
    }
}