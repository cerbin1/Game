package app.game;

import app.game.card.Card;
import app.game.card.nobility.Nobility;
import app.game.token.TokenColor;
import app.game.token.Tokens;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Card> boughtCards = new ArrayList<>();
    private final List<Nobility> nobilities = new ArrayList<>();
    private Tokens tokens;

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

    public boolean hasCards() {
        return !boughtCards.isEmpty();
    }

    public void addCard(Card card) {
        boughtCards.add(card);
    }

    public void addNobility(Nobility nobility) {
        nobilities.add(nobility);
    }

    public int getPoints() {
        return getPointsFromCards() + getPointsFromNobility();
    }

    public void setTokens(Tokens tokens) {
        this.tokens = tokens;
    }

    private int getPointsFromCards() {
        return boughtCards.stream().filter(card -> !card.isReserved()).mapToInt(Card::getPoints).sum();
    }

    private int getPointsFromNobility() {
        return nobilities.stream().mapToInt(Nobility::getPoints).sum();
    }
}
