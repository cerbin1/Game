package app.model;

import app.model.card.Card;
import app.model.card.nobility.Nobility;
import app.model.token.TokensAmount;
import app.model.turn.Turn;

import java.util.List;

public class Game {
    private TokensAmount tokensAmount;
    private final List<Card> cards;
    private final List<Player> players;
    private final List<Nobility> nobilities;
    private int currentPlayer = 0;

    public Game(TokensAmount tokensAmount, List<Player> players, List<Card> cards, List<Nobility> nobilities) {
        this.tokensAmount = tokensAmount;
        this.players = players;
        this.cards = cards;
        this.nobilities = nobilities;
    }

    public TokensAmount getTokensAmount() {
        return tokensAmount;
    }

    public int getVersatile() {
        return tokensAmount.getVersatile();
    }

    public void setTokensAmount(TokensAmount tokensAmount) {
        this.tokensAmount = tokensAmount;
    }

    public void decVersatile() {
        tokensAmount = tokensAmount.subtract(new TokensAmount(0, 1));
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

    List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    List<Nobility> getNobilities() {
        return nobilities;
    }

    public void performTurn(Turn turn) {
        turn.invoke(this);
        currentPlayer = (currentPlayer + 1) % players.size();
    }
}
