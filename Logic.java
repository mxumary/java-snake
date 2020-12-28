import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

// holds game mechanics/logic
@SuppressWarnings("serial")
public class Logic extends JPanel {
    
    private GrowTreat t;
    private SnakeChunk s;
    private SpeedTreat spt;
    
    public static final int BOARD_WIDTH = 650;
    public static final int BOARD_HEIGHT = 600;
    private boolean playing;
    private JLabel status;
    private static int interval = 120;
    public static final int SNAKE_VELOCITY = 1;
    private static int currentInterval = 120;
    private Timer timer;
    
    
    public Logic(JLabel statusInit) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        status = statusInit;
        timer = new Timer(interval, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT
                        && s.getCurDir() != 1 && s.getCurDir() != 0) {
                    s.setVx(-SNAKE_VELOCITY);
                    s.setVy(0);
                    s.setCurDir(1); // left
                    s.move();
                }
                
                // turn right if the snake isn't already going left or right
                if (e.getKeyCode() == KeyEvent.VK_RIGHT
                        && s.getCurDir() != 1 && s.getCurDir() != 0) {
                    s.setVx(SNAKE_VELOCITY);
                    s.setVy(0);
                    s.setCurDir(0); // right
                    s.move();
                }
                
                // turn up if the snake isn't already going up or down
                if (e.getKeyCode() == KeyEvent.VK_UP
                        && s.getCurDir() != 2 && s.getCurDir() != 3) {
                    s.setVy(-SNAKE_VELOCITY);
                    s.setVx(0);
                    s.setCurDir(3); // up 
                    s.move();
                }
                
                // turn down if the snake isn't already going up or down
                if (e.getKeyCode() == KeyEvent.VK_DOWN
                        && s.getCurDir() != 2 && s.getCurDir() != 3) {
                    System.out.println("hi");
                    s.setVy(SNAKE_VELOCITY);
                    s.setVx(0);
                    s.setCurDir(2); // down
                    s.move();
                }
            }
        });
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        t.draw(g);
        s.draw(g);
        spt.draw(g);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
    
    public void gameOver() {
        int score = s.getBody().size();
        String username = JOptionPane.showInputDialog("Game over. You scored: " 
            + score + ". Please enter username below.");
        FileWriter fw;
        try {
            fw = new FileWriter(new File("scores.txt"), true);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(score + "." + username + "\n");
            
            writer.close();
            FileReader fr = new FileReader(new File("scores.txt"));
            BufferedReader br = new BufferedReader(fr);
            String line;
            List<UserScore> scores = new ArrayList<UserScore>();
            while ((line = br.readLine()) != null) {
                UserScore currentUserScore = new UserScore(
                        Integer.parseInt(line.substring(0, line.indexOf('.'))), 
                                line.substring(line.indexOf('.') + 1));
                if (currentUserScore != null) { // check if null or every element is valid
                    scores.add(currentUserScore); // this is not even being added
                }
            }
            br.close();
            Collections.sort(scores, new UserScore());
            String scoresToPrint = "Here are the high scores: \n";
            // add a conditional 
            if (scores.size() < 3) {
                scoresToPrint = "Fewer than 3 people have played this game. Scores: \n" +
                        "";
                for (int i = 0; i < scores.size(); i++) {
                    scoresToPrint += scores.get(i).getUser() + ": " + scores.get(i).getScore() 
                            + "\n";
                }
            } else {
                for (int i = 0; i < 3; i++) { // we only want the top three scores
                    scoresToPrint += scores.get(i).getUser() + ": " + scores.get(i).getScore() 
                            + "\n";
                }
            }
            System.out.print(scoresToPrint);
            JOptionPane.showMessageDialog(null, scoresToPrint);
        } catch (FileNotFoundException e) {
            System.out.print("file is not found");
            throw new IllegalArgumentException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    
    void tick() {
        if (playing) {
            s.move();
            repaint();
            if (currentInterval != interval) { // turns timer back into default interval
                currentInterval = interval;
                timer.setDelay(interval);
                timer.restart();
            }
            if (s.hitWall() || s.selfCollision()) {
                playing = false;
                status.setText("You lose!");
                gameOver();
            }
            if (s.intersects(t)) {
                t.effect(s);
                t = new GrowTreat(BOARD_WIDTH, BOARD_HEIGHT, Color.RED);
            }
            if (s.intersects(spt)) { 
                spt.effect(s);
                spt = new SpeedTreat(BOARD_WIDTH, BOARD_HEIGHT, Color.ORANGE);
            }
        }
    }
    
    public void reset() {
        t = new GrowTreat(BOARD_WIDTH, BOARD_HEIGHT, Color.RED);
        s = new SnakeChunk(BOARD_WIDTH, BOARD_HEIGHT, Color.GREEN);
        spt = new SpeedTreat(BOARD_WIDTH, BOARD_HEIGHT, Color.ORANGE);
        playing = true;
        status.setText("Running...");
        currentInterval = 120;
        interval = 120;
        timer.setDelay(120);
        timer.restart();

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }
    public static int getInterval() {
        return interval;
    }
    public static void setInterval(int c) {
        interval = c;
    }
    public static int getCurrentInterval() {
        return currentInterval;
    }
    public static void setCurrentInterval(int c) {
        currentInterval = c;
    }
}
