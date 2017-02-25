package game;

public class Game {
    static Tokens tokens = new Tokens(7, 5);
    private Player[] players = new Player[4];

    private void startGame() {
        initializePlayers();
    }

    private void initializePlayers() {
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(new Tokens(0, 0));
        }
    }

    public static void main(String[] args) {
        new Game().startGame();
    }
}
