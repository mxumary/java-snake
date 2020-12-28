// each snake is made up of a LinkedList of points
public class Point {

    private int x;
    private int y;

    public Point(int initX, int initY) {
        x = initX;
        y = initY;
    }

    // getters and setters
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    public void setX(int x2) {
        x = x2;
    }
    
    public void setY(int y2) {
        y = y2;
    }
    
    // equals
    public boolean equals(Point p) {
        // if x coordinates don't match, false
        if (this.getX() != p.getX()) {
            return false;
        }
        if (this.getY() != p.getY()) {
            return false;
        }
        return true;
    }
    
}
