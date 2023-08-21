
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGamePanel extends JPanel {

    private Snake snake;
    private Point apple;

    public SnakeGamePanel() {
        snake = new Snake();
        apple = new Point(10, 10); // Inizializza la posizione della mela

        setPreferredSize(new Dimension(400, 400)); // Imposta le dimensioni del pannello

        // Aggiungi il key listener per le frecce direzionali
        addKeyListener(new KeyAdapter());
        setFocusable(true);

        // Imposta il timer per aggiornare il gioco
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aggiorna la posizione del serpente e controlla le collisioni
                snake.move();
                checkCollisions();
                repaint();
            }
        });
        timer.start();
    }

    private void checkCollisions() {
        // Controlla se il serpente ha mangiato la mela
        if (snake.getHead().equals(apple)) {
            snake.grow();
            // Genera una nuova posizione per la mela
            apple.setLocation(Math.random() * getWidth(), Math.random() * getHeight());
        }

        // Controlla le collisioni con i bordi e il corpo del serpente
        if (snake.collidesWithBorder(getWidth(), getHeight()) || snake.collidesWithSelf()) {
            // Implementa la logica per terminare il gioco
            System.out.println("Game Over");
            System.exit(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Disegna il serpente
        snake.draw(g);

        // Disegna la mela
        g.setColor(Color.RED);
        g.fillRect(apple.x, apple.y, 10, 10); // Disegna un rettangolo per la mela
    }

    private class KeyAdapter implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            // Aggiorna la direzione del serpente in base all'input delle frecce direzionali
            snake.setDirection(e.getKeyCode());
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}
    }
}