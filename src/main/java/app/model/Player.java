package app.model;

import app.model.card.Card;
import app.model.card.nobility.Nobility;
import app.model.token.Resources;
import app.model.token.TokenColor;
import app.model.token.Tokens;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static app.model.token.TokenColor.values;

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

    public Resources getResources() {
        return new Resources(getTokensFromCards(), getTokens());
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean hasCard(Card card) {
        return cards.contains(card);
    }

    public boolean hasCards() {
        return !cards.isEmpty();
    }

    void addNobility(Nobility nobility) {
        nobilities.add(nobility);
    }

    public Tokens getTokens() {
        return tokens;
    }

    public void setTokens(Tokens tokens) {
        this.tokens = tokens;
    }

    private Tokens getTokensFromCards() {
        Map<TokenColor, Integer> tokensFromCards = new EnumMap<>(TokenColor.class);
        for (TokenColor color : values()) {
            tokensFromCards.put(color, (int) cards.stream().filter(card -> card.is(color) && !card.isReserved()).count());
        }
        return new Tokens(tokensFromCards, 0);
    }

    public void incVersatile() {
        tokens = tokens.add(new Tokens(0, 1));
    }

    int getPoints() {
        return getPointsFromCards() + getPointsFromNobility();
    }

    private int getPointsFromCards() {
        return cards.stream().filter(card -> !card.isReserved()).mapToInt(Card::getPoints).sum();
    }

    private int getPointsFromNobility() {
        return nobilities.stream().mapToInt(Nobility::getPoints).sum();
    }
}
