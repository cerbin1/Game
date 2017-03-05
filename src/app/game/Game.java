package app.game;

import app.game.card.Card;
import app.game.card.nobility.Nobility;
import app.game.token.Tokens;
import app.game.turn.Turn;
import app.util.Lists;

import java.util.List;

public class Game {
    private Tokens tokens;
    private List<Card> cards;
    private List<Player> players;
    private List<Nobility> nobilities;
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
        return Lists.join(cards);
    }

    public void performTurn(Turn turn) {
        turn.invoke(this);

        currentPlayer = (currentPlayer + 1) % players.size();
    }

    public static void main(String[] args) {
        new GameBuilder().create();
    }

    public void setTokens(Tokens tokens) {
        this.tokens = tokens;
    }
}
