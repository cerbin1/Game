package app.game.turn;

import app.game.Game;
import app.game.Player;
import app.game.card.Card;
import app.game.card.CheapCard;
import app.game.token.Tokens;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static app.game.GameBuilder.builder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReservationTurnTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldReserveCard() {
        // given
        Card card = new CheapCard();
        Turn turn = new ReservationTurn(card);
        Game game = builder().add(new Player()).add(card).create();

        // when
        turn.invoke(game);
        // then

        assertTrue(card.isReserved());
    }

    @Test
    public void shouldMoveCardFromGameToPlayer() {
        // given
        Card card = new CheapCard();
        Turn turn = new ReservationTurn(card);
        Player player = new Player();
        Game game = builder().add(card).add(player).create();

        // when
        turn.invoke(game);

        // then
        assertTrue(game.getAvailableCards().isEmpty());
        assertEquals(card, player.getCards().get(0));
    }

    @Test
    public void shouldMoveTokenFromGameToPlayer() {
        // given
        Card card = new CheapCard();
        Turn turn = new ReservationTurn(card);
        Player player = new Player();
        Game game = builder().add(player).add(card).set(new Tokens(1)).create();

        // when
        turn.invoke(game);

        // then
        assertEquals(new Tokens(), game.getTokens());
        assertEquals(new Tokens(1), player.getTokens());
    }

    @Test
    public void shouldNotMoveTokenFromGameToPlayer() {
        // given
        Card card = new CheapCard();
        Turn turn = new ReservationTurn(card);
        Player player = new Player();
        Game game = builder().add(player).add(card).create();

        game.setTokens(new Tokens());

        // when
        turn.invoke(game);

        // then
        assertEquals(new Tokens(), player.getTokens());
    }

    @Test(expected = IllegalTurnException.class)
    public void shouldThrowOnMissingCard() {
        // given
        Turn turn = new ReservationTurn(new CheapCard());
        Game game = builder().add(new Player()).create();

        expectedException.expect(IllegalTurnException.class);
        expectedException.expectMessage("Card is not available in game");

        // when
        turn.invoke(game);
    }

    @Test(expected = IllegalTurnException.class)
    public void shouldThrowOnAlreadyReservedCard() {
        // given
        CheapCard card = new CheapCard();
        Turn turn = new ReservationTurn(card);
        Game game = builder().add(new Player()).add(card).create();

        card.setReserved(true);

        expectedException.expect(IllegalTurnException.class);
        expectedException.expectMessage("Card already reserved");

        // when
        turn.invoke(game);
    }

    @Test(expected = IllegalTurnException.class)
    public void shouldThrowOnPossessedCard() {
        // given
        CheapCard card = new CheapCard();
        Player player = new Player();
        Turn turn = new ReservationTurn(card);
        Game game = builder().add(player).add(card).create();

        player.addCard(card);

        expectedException.expect(IllegalTurnException.class);
        expectedException.expectMessage("Card is already possessed by player");

        // when
        turn.invoke(game);
    }
}
