import java.awt.*;
import java.util.LinkedList;

//This class controls the movement for the snake and looks for collisions
public class SnakeChunk extends GameObj {
        
    private static final int SIZE = 20;
    int width = 20;
    int height = 20;
    // directions
    public static final int NO_MOVE = 4;
    
    // Head of the snake: 
    public static final int INIT_POS_X = 5;
    public static final int INIT_POS_Y = 5;

    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    
    // helps prevent the snake from bumping into itself when it tries to
    // turn the opposite direction from the direction it is currently facing 
    private int curDir;
    
    
    // snake body parts
    private LinkedList<Point> body;
    
    // color
    private Color color;

    // constructor
    public SnakeChunk(int courtWidth, int courtHeight, Color color) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);
        this.curDir = NO_MOVE;
        body = new LinkedList<Point>();
        body.add(new Point(INIT_POS_X, INIT_POS_Y)); 
        this.color = color;
    }
    
    // getters and setters for curDir and nextDir
    public int getCurDir() {
        return this.curDir;
    }
    public void setCurDir(int curDir) {
        this.curDir = curDir;
    }

    @Override
    public void move() {
        if (getVx() != 0 || getVy() != 0) {
            Point hd = body.peekFirst();
            int oldX = hd.getX();
            int oldY = hd.getY();
            if (getVx() < 0) { // left, moves head
                hd.setX(hd.getX() - SIZE);
                hd.setY(hd.getY());
                setPx(hd.getX() - SIZE);
                setPy(hd.getY());
            }
            if (getVx() > 0) { //right, moves head
                hd.setX(hd.getX() + SIZE);
                hd.setY(hd.getY());
                setPx(hd.getX() + SIZE);
                setPy(hd.getY());
            }
            if (getVy() < 0) { // up, moves head
                hd.setX(hd.getX());
                hd.setY(hd.getY() - SIZE);
                setPx(hd.getX());
                setPy(hd.getY() - SIZE);
            }
            if (getVy() > 0) { //down, moves head
                hd.setX(hd.getX());
                hd.setY(hd.getY() + SIZE);
                setPx(hd.getX());
                setPy(hd.getY() + SIZE);
            }
            for (int i = 1; i < body.size(); i++) {
                // the ith chunk goes into the space the preceding chunk was at.
                Point p = body.get(i);
                int prevX = p.getX();
                int prevY = p.getY();
                p.setX(oldX); // for i = 1, oldX and oldY is the snake head
                p.setY(oldY);
                oldX = prevX; 
                oldY = prevY;
            }
        }
    }
    
    public void draw(Graphics g) {
        g.setColor(this.color);
        for (int i = 0; i < body.size(); i++) {
            g.fillRect((int)body.get(i).getX(), (int)body.get(i).getY(), this.getWidth(), 
                    this.getHeight());
        }
    }
    
    public void grow() {
        Point p = body.get(body.size() - 1);
        int x = p.getX();
        int y = p.getY();
        body.add(new Point(x, y));
    }
      
    public boolean selfCollision() {
        Point hd = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            Point p = body.get(i);
            if (hd.getX() == p.getX() &&
                    hd.getY() == p.getY()) {
                return true;
            }
        }
        return false;
    }
    
    
    // getters and setters
    public LinkedList<Point> getBody() {
        return body;
    }

    public void setBody(LinkedList<Point> body) {
        this.body = body;
    }
    
}