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
        this.players = players;
        this.tokens = tokens;
        this.cards = cards;
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

    public List<Nobility> getNobility() {
        return nobilities;
    }

    public List<Card> getAvailableCards() {
        return cards;
    }

    public void setTokens(Tokens cost) {
        tokens = new Tokens(tokens.getGreen() + cost.getGreen(), tokens.getPurple() + cost.getPurple(), tokens.getBlue() + cost.getBlue(), tokens.getBlack() + cost.getBlack(), tokens.getRed() + cost.getRed(), tokens.getVersatile() - cost.getVersatile());
    }

    public void performTurn(Turn turn) {
        turn.invoke(this);
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    public void decVersatile(int value) {
        tokens = tokens.subtract(new Tokens(0, value));
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public static void main(String[] args) {
        new GameBuilder().create();
    }

    public boolean hasCard(Card card) {
        return cards.contains(card);
    }
}
