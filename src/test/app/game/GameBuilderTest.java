package app.game;

import app.game.card.Card;
import app.game.card.CheapCard;
import app.game.card.nobility.Nobility;
import app.game.token.Tokens;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class GameBuilderTest {
    @Test
    public void shouldSetTokens() {
        // given
        GameBuilder builder = new GameBuilder();
        Tokens tokens = new Tokens();

        // when
        Game game = builder.set(tokens).add(new Player()).create();

        // then
        assertEquals(game.getTokens(), tokens);
    }

    @Test
    public void shouldAddPlayer() {
        // given
        GameBuilder builder = new GameBuilder();
        Player first = new Player(), second = new Player();

        // when
        Game game = builder.add(first).add(second).create();

        // then
        assertEquals(game.getPlayers(), asList(first, second));
        assertEquals(game.getCurrentPlayer(), first);
    }

    @Test
    public void shouldAddCard() {
        // given
        GameBuilder builder = new GameBuilder();
        Card first = new CheapCard(), second = new CheapCard();

        // when
        Game game = builder.add(first).add(second).add(new Player()).create();

        // then
        assertEquals(game.getAvailableCards(), asList(first, second));
    }

    @Test
    public void shouldAddNobility() {
        // given
        GameBuilder builder = new GameBuilder();
        Nobility first = new Nobility(new Tokens(), 4),
                second = new Nobility(new Tokens(), 3);

        // when
        Game game = builder.add(first).add(second).add(new Player()).create();

        // then
        assertEquals(game.getNobility(), asList(first, second));
    }
}
