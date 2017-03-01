package game;

import game.cards.Card;

import java.util.List;

public class Player {
    private final Tokens tokens;
    private List<Card> boughtCards;

    public Player() {
        this(new Tokens());
    }

    public Player(Tokens tokens) {
        this.tokens = tokens;
    }

    public Tokens getTokens() {
        return tokens;
    }

    public List<Card> getCards() {
        return boughtCards;
    }
}
