package app.game;

import app.game.card.Card;
import app.game.card.nobility.Nobility;
import app.game.token.Tokens;

import java.util.ArrayList;
import java.util.List;

import static app.game.token.TokenColor.*;

public class Player {
    private final List<Card> cards = new ArrayList<>();
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
        return new Tokens(
                (int) (tokens.getGreen() + cards.stream().filter(card -> card.is(Green) && !card.isReserved()).count()),
                (int) (tokens.getPurple() + cards.stream().filter(card -> card.is(Purple) && !card.isReserved()).count()),
                (int) (tokens.getBlue() + cards.stream().filter(card -> card.is(Blue) && !card.isReserved()).count()),
                (int) (tokens.getBlack() + cards.stream().filter(card -> card.is(Black) && !card.isReserved()).count()),
                (int) (tokens.getRed() + cards.stream().filter(card -> card.is(Red) && !card.isReserved()).count()),
                tokens.getVersatile()
        );
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean hasCards() {
        return !cards.isEmpty();
    }

    public void addCard(Card card) {
        cards.add(card);
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

    public void incVersatile(int value) {
        tokens = tokens.add(new Tokens(0, value));
    }

    private int getPointsFromCards() {
        return cards.stream().filter(card -> !card.isReserved()).mapToInt(Card::getPoints).sum();
    }

    private int getPointsFromNobility() {
        return nobilities.stream().mapToInt(Nobility::getPoints).sum();
    }

    public boolean isAbleToBuyCard(Tokens cost) {
        Tokens playerTokens = getTokensIncludingBoughtCards();
        return playerTokens.getGreen() >= cost.getGreen() && playerTokens.getPurple() >= cost.getPurple() && playerTokens.getBlue() >= cost.getBlue() && playerTokens.getBlack() >= cost.getBlack() && playerTokens.getRed() >= cost.getRed();
    }

    public boolean hasCard(Card card) {
        return cards.contains(card);
    }
}
