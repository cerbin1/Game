package app.game;

import app.game.card.Card;
import app.game.card.nobility.Nobility;
import app.game.token.TokenColor;
import app.game.token.Tokens;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Tokens tokens;
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

    public Tokens getTokensIncludingBoughtCards() {
        return new Tokens((int) (tokens.getGreen() + boughtCards.stream().filter(token -> token.getColor() == TokenColor.Green).count()), (int) (tokens.getPurple() + boughtCards.stream().filter(token -> token.getColor() == TokenColor.Purple).count()),
                (int) (tokens.getBlue() + boughtCards.stream().filter(token -> token.getColor() == TokenColor.Blue).count()), (int) (tokens.getBlack() + boughtCards.stream().filter(token -> token.getColor() == TokenColor.Black).count()),
                (int) (tokens.getRed() + boughtCards.stream().filter(token -> token.getColor() == TokenColor.Red).count()));
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

    public void setTokens(Tokens tokens) {
        this.tokens = tokens;
    }
}
