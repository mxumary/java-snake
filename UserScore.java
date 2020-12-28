import java.util.Comparator;

public class UserScore implements Comparator<UserScore>, Comparable<UserScore> {
    
    private String user; 
    private int score;
    
    public UserScore() {
        
    }
    
    public UserScore(int score, String user) {
        this.user = user;
        this.score = score;
    }
    
    @Override
    public int compareTo(UserScore o) {
        return o.score - this.score;
    }

    @Override
    public int compare(UserScore o1, UserScore o2) {
        return o2.score - o1.score;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}