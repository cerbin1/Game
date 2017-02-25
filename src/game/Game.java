package game;

import java.util.Random;

public class Game {
    static Tokens tokens = new Tokens(7, 5);
    private Card[] cards = new Card[10];
    private Player[] players = new Player[4];

    private void startGame() {
        initializePlayers();
        initializeCharacterCards();
        setStartingPlayer();
    }

    private void initializePlayers() {
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(new Tokens(0, 0));
        }
    }

    private void initializeCharacterCards() {
        for (int i = 0; i < cards.length; i++) {
            cards[i] = new Card();
        }
    }

    private void setStartingPlayer() {
        players[drawPlayer()].setFirstPlayer();
    }

    private int drawPlayer() {
        return new Random().nextInt(3);
    }

    public static void main(String[] args) {
        new Game().startGame();
    }
}
