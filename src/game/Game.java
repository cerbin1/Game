package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private Tokens tokens = new Tokens(7, 5);
    private List<Card> cards = new ArrayList<>(10);
    private List<Player> players = new ArrayList<>(10);
    private int currentPlayer = 0;

    private void startGame() {
        initializePlayers();
        initializeCards();
        currentPlayer = drawPlayer();
    }

    private void initializePlayers() {
        for (int i = 0; i < 10; i++) {
            players.add(new Player());
        }
    }

    private void initializeCards() {
        for (int i = 0; i < 10; i++) {
            cards.add(new Card());
        }
    }

    private int drawPlayer() {
        return new Random().nextInt(players.size());
    }

    public static void main(String[] args) {
        new Game().startGame();
    }
}
