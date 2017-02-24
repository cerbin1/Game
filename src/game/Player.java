package game;

public class Player {
    private Tokens tokens = new Tokens(7, 5);
    private int points;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
