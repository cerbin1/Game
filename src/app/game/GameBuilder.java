package app.game;

import app.game.card.Card;
import app.game.card.nobility.Nobility;

import java.util.ArrayList;
import java.util.List;

public class GameBuilder {
    private Tokens tokens;
    private List<Player> players = new ArrayList<>();
    private List<Card> cheapCards = new ArrayList<>();
    private List<Card> mediumCards = new ArrayList<>();
    private List<Card> expensiveCards = new ArrayList<>();
    private List<Nobility> nobilities = new ArrayList<>();

    public Game create() {
        return new Game(tokens, players, cheapCards, mediumCards, expensiveCards, nobilities);
    }

    public void setTokens(Tokens tokens) {
        this.tokens = tokens;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addCheapCard(Card cheapCard) {
        cheapCards.add(cheapCard);
    }

    public void addMediumCard(Card mediumCard) {
        mediumCards.add(mediumCard);
    }

    public void addExpensiveCard(Card expensiveCard) {
        expensiveCards.add(expensiveCard);
    }

    public void addNobilityCard(Nobility nobility) {
        nobilities.add(nobility);
    }
}
