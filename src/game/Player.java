package game;

class Player {
    private Tokens tokens;
    private int points;
    private boolean starting;

    Player(Tokens tokens) {
        this.tokens = tokens;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points = points;
    }

    void setFirstPlayer() {
        starting = true;
    }
}
