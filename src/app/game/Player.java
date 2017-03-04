package app.game;

import app.game.card.Card;
import app.game.card.nobility.Nobility;
import app.game.token.Tokens;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final Tokens tokens;
    private final List<Card> boughtCards = new ArrayList<>();
    private final List<Nobility> nobilities = new ArrayList<>();

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

    public void addCard(Card card) {
        boughtCards.add(card);
    }

    public void addNobility(Nobility nobility) {
        nobilities.add(nobility);
    }

    public int countPoints() {
        return getPointsFromCards() + getPointsFromNobility();
    }

    private int getPointsFromCards() {
        int points = 0;
        for (Card card : boughtCards) {
            points += card.getPoints();
        }
        return points;
    }

    private int getPointsFromNobility() {
        int points = 0;
        for (Nobility nobility : nobilities) {
            points += nobility.getPoints();
        }
        return points;
    }
}
