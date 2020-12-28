import java.awt.Color;
import java.awt.Graphics;

public class SpeedTreat extends Treat {
    static int min = 20;
    static int max = 580;
    public static final int SIZE = 20;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    public static final int BOARD_WIDTH = Logic.BOARD_WIDTH;
    public static final int COURT_HEIGHT = Logic.BOARD_HEIGHT;
    
    private Color color;
    private SnakeChunk snake = new SnakeChunk(BOARD_WIDTH, COURT_HEIGHT, color);
    
    public SpeedTreat(int courtWidth, int courtHeight, Color color) {
        super(courtWidth, courtHeight, color);
        this.spawn(snake);
        this.color = color;
    }
    
    @Override
    public void effect(SnakeChunk s) {
        if (Logic.getCurrentInterval() == Logic.getInterval()) {
            Logic.setInterval(Logic.getCurrentInterval() - 10);
        }
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}
