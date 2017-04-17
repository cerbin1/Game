package app.model.turn;

import app.model.Game;
import app.model.GameBuilder;
import app.model.Player;
import app.model.token.TokensAmount;
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
        TokensAmount tokensAmount = new TokensAmount(2, 0, 0, 0, 0);
        Turn turn = new AcquireTokensTurn(tokensAmount);
        Player player = new Player();
        Game game = GameBuilder.builder().add(player).set(tokensAmount).create();

        // when
        turn.invoke(game);

        // then
        assertEquals(new TokensAmount(2, 0, 0, 0, 0), player.getTokensAmount());
        assertEquals(new TokensAmount(), game.getTokensAmount());
    }

    @Test
    public void shouldAcquireThreeDifferentTokens() {
        // given
        TokensAmount tokensAmount = new TokensAmount(1, 1, 1, 0, 0);
        Turn turn = new AcquireTokensTurn(tokensAmount);
        Player player = new Player();
        Game game = GameBuilder.builder().add(player).set(tokensAmount).create();

        // when
        turn.invoke(game);

        // then
        assertEquals(new TokensAmount(1, 1, 1, 0, 0), player.getTokensAmount());
        assertEquals(new TokensAmount(), game.getTokensAmount());
    }

    @Test
    public void shouldThrowOnAcquireNoTokens() {
        // given
        TokensAmount tokensAmount = new TokensAmount();
        Turn turn = new AcquireTokensTurn(tokensAmount);
        Player player = new Player();
        Game game = GameBuilder.builder().add(player).set(tokensAmount).create();

        expectedException.expect(IllegalTurnException.class);
        expectedException.expectMessage("Invalid amount of tokensAmount");

        // when
        turn.invoke(game);
    }

    @Test
    public void shouldThrowOnAcquiringInvalidAmountOfTokens() {
        // given
        TokensAmount tokensAmount = new TokensAmount(1, 0, 0, 0, 0);
        Turn turn = new AcquireTokensTurn(tokensAmount);
        Player player = new Player();
        Game game = GameBuilder.builder().add(player).set(tokensAmount).create();

        expectedException.expect(IllegalTurnException.class);
        expectedException.expectMessage("Invalid amount of tokensAmount");

        // then
        turn.invoke(game);
    }

    @Test
    public void shouldThrowOnAcquireMoreTokensThanInGame() {
        // given
        TokensAmount tokensAmountToAcquire = new TokensAmount(2, 0, 0, 0, 0);
        Turn turn = new AcquireTokensTurn(tokensAmountToAcquire);
        Player player = new Player();
        Game game = GameBuilder.builder().add(player).set(new TokensAmount()).create();

        expectedException.expect(IllegalTurnException.class);
        expectedException.expectMessage("Not enough tokens in game");

        // when
        turn.invoke(game);
    }
}
