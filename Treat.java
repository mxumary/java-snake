import java.awt.Color;
import java.awt.Graphics;

public abstract class Treat extends GameObj {
    static int min = 20;
    static int max = 575;
    public static final int SIZE = 20;
    private static int x = (int) (Math.random() * (max - min + 1) + min);
    private static int y = (int) (Math.random() * (max - min + 1) + min);
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    
    public static final int BOARD_WIDTH = Logic.BOARD_WIDTH;
    public static final int COURT_HEIGHT = Logic.BOARD_HEIGHT;
    
    private Color color;
    private SnakeChunk snake = new SnakeChunk(BOARD_WIDTH, COURT_HEIGHT, color);

    public Treat(int courtWidth, int courtHeight, Color color) {
        super(INIT_VEL_X, INIT_VEL_Y, x, y, SIZE, SIZE, courtWidth, courtHeight);
        this.spawn(snake);
        this.color = color;
    }
    
    public void spawn(SnakeChunk snake) {
        x = (int) (Math.random() * (max - min + 1) + min);
        y = (int) (Math.random() * (max - min + 1) + min);
        for (int i = 0; i < snake.getBody().size(); i++) { // check if treat is on snake
            if (snake.getBody().get(i).getX() == x && snake.getBody().get(i).getY() == y) {
                x = (int) (Math.random() * (max - min + 1) + min);
                y = (int) (Math.random() * (max - min + 1) + min);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
    
    public abstract void effect(SnakeChunk s);
    
    public static int getX() {
        return x;
    }

    public static void setX(int x) {
        Treat.x = x;
    }

    public static int getY() {
        return y;
    }

    public static void setY(int y) {
        Treat.y = y;
    }
}
