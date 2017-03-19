package app.game;

import app.game.card.Card;
import app.game.card.nobility.Nobility;
import app.game.token.Tokens;

import java.util.ArrayList;
import java.util.List;

public class GameBuilder {
    private Tokens tokens = new Tokens();
    private final List<Card> cards = new ArrayList<>();
    private final List<Player> players = new ArrayList<>();
    private final List<Nobility> nobilities = new ArrayList<>();

    public Game create() {
        if (players.isEmpty()) throw new RuntimeException("Missing players");
        return new Game(tokens, players, cards, nobilities);
    }

    public static GameBuilder builder() {
        return new GameBuilder();
    }

    public GameBuilder set(Tokens tokens) {
        this.tokens = tokens;
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
