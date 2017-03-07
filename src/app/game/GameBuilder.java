package app.game;

import app.game.card.Card;
import app.game.card.nobility.Nobility;
import app.game.token.Tokens;

import java.util.ArrayList;
import java.util.List;

public class GameBuilder {
    private Tokens tokens;
    private final List<Card> cards = new ArrayList<>();
    private final List<Player> players = new ArrayList<>();
    private final List<Nobility> nobilities = new ArrayList<>();

    public Game create() {
        return new Game(tokens, players, cards, nobilities);
    }

    public void setTokens(Tokens tokens) {
        this.tokens = tokens;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addNobility(Nobility nobility) {
        nobilities.add(nobility);
    }
}
