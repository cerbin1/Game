package app.model.turn;

import app.model.Game;
import app.model.GameBuilder;
import app.model.Player;
import app.model.token.Tokens;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class AcquireTokensTurnTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldAcquireTwoSameTokens() {
        // given
        Tokens tokens = new Tokens(2, 0, 0, 0, 0);
        Turn turn = new AcquireTokensTurn(tokens);
        Player player = new Player();
        Game game = GameBuilder.builder().add(player).set(tokens).create();

        // when
        turn.invoke(game);

        // then
        assertEquals(new Tokens(2, 0, 0, 0, 0), player.getTokens());
        assertEquals(new Tokens(), game.getTokens());
    }

    @Test
    public void shouldAcquireThreeDifferentTokens() {
        // given
        Tokens tokens = new Tokens(1, 1, 1, 0, 0);
        Turn turn = new AcquireTokensTurn(tokens);
        Player player = new Player();
        Game game = GameBuilder.builder().add(player).set(tokens).create();

        // when
        turn.invoke(game);

        // then
        assertEquals(new Tokens(1, 1, 1, 0, 0), player.getTokens());
        assertEquals(new Tokens(), game.getTokens());
    }

    @Test
    public void shouldThrowOnAcquireNoTokens() {
        // given
        Tokens tokens = new Tokens();
        Turn turn = new AcquireTokensTurn(tokens);
        Player player = new Player();
        Game game = GameBuilder.builder().add(player).set(tokens).create();

        expectedException.expect(IllegalTurnException.class);
        expectedException.expectMessage("Invalid amount of tokens");

        // when
        turn.invoke(game);
    }

    @Test
    public void shouldThrowOnAcquiringInvalidAmountOfTokens() {
        // given
        Tokens tokens = new Tokens(1, 0, 0, 0, 0);
        Turn turn = new AcquireTokensTurn(tokens);
        Player player = new Player();
        Game game = GameBuilder.builder().add(player).set(tokens).create();

        expectedException.expect(IllegalTurnException.class);
        expectedException.expectMessage("Invalid amount of tokens");

        // then
        turn.invoke(game);
    }

    @Test
    public void shouldThrowOnAcquireMoreTokensThanInGame() {
        // given
        Tokens tokensToAcquire = new Tokens(2, 0, 0, 0, 0);
        Turn turn = new AcquireTokensTurn(tokensToAcquire);
        Player player = new Player();
        Game game = GameBuilder.builder().add(player).set(new Tokens()).create();

        expectedException.expect(IllegalTurnException.class);
        expectedException.expectMessage("Not enough tokens in game");

        // when
        turn.invoke(game);
    }
}
