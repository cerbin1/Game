package app.model;

import app.model.card.Card;
import app.model.card.nobility.Nobility;
import app.model.token.Tokens;
import app.model.turn.Turn;

import java.util.List;

public class Game {
    private Tokens tokens;
    private final List<Card> cards;
    private final List<Player> players;
    private final List<Nobility> nobilities;
    private int currentPlayer = 0;

    public Game(Tokens tokens, List<Player> players, List<Card> cards, List<Nobility> nobilities) {
        this.tokens = tokens;
        this.players = players;
        this.cards = cards;
        this.nobilities = nobilities;
    }

    public Tokens getTokens() {
        return tokens;
    }

    public void setTokens(Tokens tokens) {
        this.tokens = tokens;
    }

    public void decVersatile() {
        tokens = tokens.subtract(new Tokens(0, 1));
    }

    public List<Card> getAvailableCards() {
        return cards;
    }

    public boolean hasCard(Card card) {
        return cards.contains(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Nobility> getNobility() {
        return nobilities;
    }

    public void performTurn(Turn turn) {
        turn.invoke(this);
        currentPlayer = (currentPlayer + 1) % players.size();
    }
}
