package app.model;

import app.model.card.Card;
import app.model.card.nobility.Nobility;
import app.model.token.TokensAmount;

import java.util.ArrayList;
import java.util.List;

public class GameBuilder {
    private TokensAmount tokensAmount = new TokensAmount();
    private final List<Player> players = new ArrayList<>();
    private final List<Card> cards = new ArrayList<>();
    private final List<Nobility> nobilities = new ArrayList<>();

    public Game create() {
        if (players.isEmpty()) throw new RuntimeException("Missing players");
        return new Game(tokensAmount, players, cards, nobilities);
    }

    public static GameBuilder builder() {
        return new GameBuilder();
    }

    public GameBuilder set(TokensAmount tokensAmount) {
        this.tokensAmount = tokensAmount;
        return this;
    }

    public GameBuilder add(Player player) {
        players.add(player);
        return this;
    }

    public GameBuilder add(Card card) {
        cards.add(card);
        return this;
    }

    public GameBuilder add(Nobility nobility) {
        nobilities.add(nobility);
        return this;
    }
}
