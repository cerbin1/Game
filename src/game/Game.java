package game;

import game.cards.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private Tokens tokens;
    private List<Card> cheapCards;
    private List<Card> mediumCards;
    private List<Card> expensiveCards;

    private List<Player> players = new ArrayList<>();

    public Game(Tokens tokens, List<Player> players, List<Card> cheapCards, List<Card> mediumCards, List<Card> expensiveCards) {
        this.players = players;
        this.tokens = tokens;
        this.cheapCards = cheapCards;
        this.mediumCards = mediumCards;
        this.expensiveCards = expensiveCards;
    }

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
            cheapCards.add(new Card());
            mediumCards.add(new Card());
            expensiveCards.add(new Card());
        }
    }

    private int drawPlayer() {
        return new Random().nextInt(players.size());
    }

    public List<Card> getCheapCards() {
        return cheapCards;
    }

    public List<Card> getMediumCards() {
        return mediumCards;
    }

    public List<Card> getExpensiveCards() {
        return expensiveCards;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Tokens getTokens() {
        return tokens;
    }

    public static void main(String[] args) {
        new GameFactory().create();
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }
}
