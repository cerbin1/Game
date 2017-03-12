package app.game;

import app.game.card.Card;
import app.game.card.CheapCard;
import app.game.turn.PassTurn;
import org.junit.Test;

import static app.game.GameBuilder.builder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {
    @Test
    public void shouldHaveFirstPlayer() {
        // given
        Player player = new Player();
        Game game = builder().add(player).create();

        // when
        Player currentPlayer = game.getCurrentPlayer();

        // then
        assertEquals(player, currentPlayer);
    }

    @Test
    public void shouldChangeCurrentPlayerToNext() {
        // given
        Player second = new Player();
        Game game = builder().add(new Player()).add(second).create();
        game.performTurn(new PassTurn());

        // when
        Player currentPlayer = game.getCurrentPlayer();

        // then
        assertEquals(second, currentPlayer);
    }

    @Test
    public void shouldGetBackToPlayerAfterTwoTurns() {
        // given
        Player first = new Player(), second = new Player();
        Game game = builder().add(first).add(second).create();

        game.performTurn(new PassTurn());
        game.performTurn(new PassTurn());

        // when
        Player currentPlayer = game.getCurrentPlayer();

        // then
        assertEquals(first, currentPlayer);
    }

    @Test
    public void shouldHaveCard() {
        // given
        Card card = new CheapCard();

        // when
        Game game = builder().add(card).add(new Player()).create();

        // then
        assertTrue(game.hasCard(card));
    }
}
