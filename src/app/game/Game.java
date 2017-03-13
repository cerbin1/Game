package app.game;

import app.game.card.Card;
import app.game.card.nobility.Nobility;
import app.game.token.Tokens;
import app.game.turn.Turn;

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

    public void decVersatile(int value) {
        tokens = tokens.subtract(new Tokens(0, value));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
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

    public List<Nobility> getNobility() {
        return nobilities;
    }

    public void performTurn(Turn turn) {
        turn.invoke(this);
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    public static void main(String[] args) {
        new GameBuilder().create();
    }
}
