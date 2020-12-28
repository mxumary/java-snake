import java.awt.Color;
import java.awt.Graphics;

public class GrowTreat extends Treat {
    static int min = 20;
    static int max = 580;
    public static final int SIZE = 20;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    public static final int BOARD_WIDTH = Logic.BOARD_WIDTH;
    public static final int COURT_HEIGHT = Logic.BOARD_HEIGHT;

    private Color color;
    private SnakeChunk snake = new SnakeChunk(BOARD_WIDTH, COURT_HEIGHT, color);

    public GrowTreat(int courtWidth, int courtHeight, Color color) {
        super(courtWidth, courtHeight, color);
        this.spawn(snake);
        this.color = color;
    }

    public void effect(SnakeChunk s) {
        s.grow();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }

}
