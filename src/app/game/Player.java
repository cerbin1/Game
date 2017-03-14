package app.game;

import app.game.card.Card;
import app.game.card.nobility.Nobility;
import app.game.token.Resources;
import app.game.token.TokenColor;
import app.game.token.Tokens;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static app.game.token.TokenColor.values;

public class Player {
    private final Map<TokenColor, Integer> dupa = new EnumMap<>(TokenColor.class);
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

    public Resources getResources() {
        return new Resources(getTokensFromCards(), getTokens());
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean hasCards() {
        return !cards.isEmpty();
    }

    public boolean hasCard(Card card) {
        return cards.contains(card);
    }

    void addNobility(Nobility nobility) {
        nobilities.add(nobility);
    }

    public void setTokens(Tokens tokens) {
        this.tokens = tokens;
    }

    private Tokens getTokensFromCards() {
        Map<TokenColor, Integer> dupa = new EnumMap<>(TokenColor.class);
        for (TokenColor color : values()) {
            dupa.put(color, (int) (cards.stream().filter(card -> card.is(color) && !card.isReserved()).count()));
        }
        return new Tokens(dupa, 0);
    }

    public void incVersatile(int value) {
        tokens = tokens.add(new Tokens(0, value));
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
