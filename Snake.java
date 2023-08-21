import java.awt.*;
import java.util.LinkedList;
import java.awt.event.KeyEvent;
public class Snake {

    private LinkedList<Point> body;
    private int direction;

    public Snake() {
        body = new LinkedList<>();
        body.add(new Point(50, 50)); // Posizione iniziale del serpente
        direction = KeyEvent.VK_RIGHT; // Direzione iniziale
    }

    public boolean collidesWithPoint(Point point) {
        for (Point bodySegment : body) {
            if (bodySegment.equals(point)) {
                return true;
            }
        }
        return false;
    }
    
    public void move() {
        Point newHead = (Point) getHead().clone();

        switch (direction) {
            case KeyEvent.VK_UP:
                newHead.translate(0, -10);
                break;
            case KeyEvent.VK_DOWN:
                newHead.translate(0, 10);
                break;
            case KeyEvent.VK_LEFT:
                newHead.translate(-10, 0);
                break;
            case KeyEvent.VK_RIGHT:
                newHead.translate(10, 0);
                break;
        }

        body.addFirst(newHead);
        body.removeLast();
    }

    public void grow() {
        Point tail = body.getLast();
        body.addLast(new Point(tail));
    }

    public Point getHead() {
        return body.getFirst();
    }

    public boolean collidesWithBorder(int width, int height) {
        Point head = getHead();
        return head.x < 0 || head.x >= width || head.y < 0 || head.y >= height;
    }

    public boolean collidesWithSelf() {
        Point head = getHead();
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).equals(head)) {
                return true;
            }
        }
        return false;
    }

    public void setDirection(int newDirection) {
        if (Math.abs(direction - newDirection) != 2) {
            direction = newDirection;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        for (Point point : body) {
            g.fillRect(point.x, point.y, 10, 10); // Disegna un rettangolo per ogni segmento del serpente
        }
    }
}