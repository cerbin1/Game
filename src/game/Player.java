package game;

class Player {
    private Tokens tokens;
    private int points;

    Player(Tokens tokens) {
        this.tokens = tokens;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points = points;
    }
}
