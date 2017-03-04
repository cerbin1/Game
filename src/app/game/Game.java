package app.game;

import app.game.card.Card;
import app.game.card.nobility.Nobility;
import app.game.turn.Turn;
import app.util.Lists;

import java.util.List;

public class Game {
    private Tokens tokens;
    private List<Card> cheapCards;
    private List<Card> mediumCards;
    private List<Card> expensiveCards;
    private List<Player> players;
    private List<Nobility> nobilities;
    private int currentPlayer = 0;

    Game(Tokens tokens, List<Player> players, List<Card> cheapCards, List<Card> mediumCards, List<Card> expensiveCards,List<Nobility> nobilities) {
        this.players = players;
        this.tokens = tokens;
        this.cheapCards = cheapCards;
        this.mediumCards = mediumCards;
        this.expensiveCards = expensiveCards;
        this.nobilities = nobilities;
    }

    public Tokens getTokens() {
        return tokens;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
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

    public List<Nobility> getNobility() {
        return nobilities;
    }

    public List<Card> getAvailableCards() {
        return Lists.join(cheapCards, mediumCards, expensiveCards);
    }

    public void performTurn(Turn turn) {
        turn.invoke(this);

        currentPlayer = (currentPlayer + 1) % players.size();
    }

    public static void main(String[] args) {
        new GameBuilder().create();
    }
}
