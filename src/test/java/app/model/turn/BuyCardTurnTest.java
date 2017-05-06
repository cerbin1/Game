package app.model.turn;

import app.model.Game;
import app.model.Player;
import app.model.card.Card;
import app.model.card.CheapCard;
import app.model.token.TokenColor;
import app.model.token.TokensAmount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static app.model.GameBuilder.builder;
import static app.model.token.TokenColor.*;
import static org.junit.Assert.*;

public class BuyCardTurnTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBuyCardToPlayer() {
        // given
        Card card = new CheapCard();
        Turn turn = new BuyCardTurn(card);
        Player player = new Player();
        Game game = builder().add(card).add(player).create();

        // when
        turn.invoke(game);

        // then
        assertTrue(player.hasCard(card));
        assertTrue(game.getAvailableCards().isEmpty());
    }

    @Test
    public void shouldLoseTokens() {
        // given
        TokensAmount cardCost = new TokensAmount(1, 2, 3, 4, 5);
        Card card = new CheapCard(cardCost, 0, Green);
        Turn turn = new BuyCardTurn(card);
        Player player = new Player(new TokensAmount(6, 0));
        Game game = builder().add(card).add(player).create();

        // when
        turn.invoke(game);

        // then
        assertEquals(new TokensAmount(5, 4, 3, 2, 1), player.getTokensAmount());
        assertEquals(cardCost, game.getTokensAmount());
    }

    @Test
    public void shouldLoseLessTokensIfHasMines() {
        // given
        Card card = new CheapCard(new TokensAmount(0, 1, 2, 1, 3), 0, Green);
        Turn turn = new BuyCardTurn(card);
        Player player = new Player(new TokensAmount(3, 0));
        Game game = builder().add(card).add(player).create();

        player.addCard(card(Purple));
        player.addCard(card(Blue));
        player.addCard(card(Red));

        // when
        turn.invoke(game);

        // then
        assertEquals(new TokensAmount(3, 3, 2, 2, 1), player.getTokensAmount());
        assertEquals(new TokensAmount(0, 0, 1, 1, 2), game.getTokensAmount());
    }

    @Test
    public void shouldBuyWithVersatile() {
        // given
        Card card = new CheapCard(new TokensAmount(2, 1, 2, 1, 2), 0, Green);
        Player player = new Player(new TokensAmount(0, 2, 1, 1, 1, 5));
        Turn turn = new BuyCardTurn(card);
        Game game = builder().add(card).add(player).create();

        // when
        turn.invoke(game);

        // then
        assertEquals(new TokensAmount(0, 1, 0, 0, 0, 1), player.getTokensAmount());
        assertEquals(new TokensAmount(0, 1, 1, 1, 1, 4), game.getTokensAmount());
    }

    @Test
    public void shouldPreferTokensOverVersatile() {
        // given
        Card card = new CheapCard(new TokensAmount(1, 0, 0, 0, 0), 0, Green);
        Player player = new Player(new TokensAmount(1, 0, 0, 0, 0, 1));
        Turn turn = new BuyCardTurn(card);
        Game game = builder().add(card).add(player).create();

        // when
        turn.invoke(game);

        // then
        assertEquals(new TokensAmount(0, 1), player.getTokensAmount());
    }

    @Test
    public void shouldBuyWithMinesAndVersatile() {
        // given
        Card card = new CheapCard(new TokensAmount(3, 0, 0, 0, 0), 0, Green);
        Player player = new Player(new TokensAmount(1, 0, 0, 0, 0, 1));
        Turn turn = new BuyCardTurn(card);
        Game game = builder().add(card).add(player).create();

        player.addCard(card(Green));

        // when
        turn.invoke(game);

        // then
        assertEquals(new TokensAmount(), player.getTokensAmount());
        assertEquals(new TokensAmount(1, 0, 0, 0, 0, 1), game.getTokensAmount());
    }

    @Test
    public void shouldNotLoseTokensOrVersatile() {
        // given
        Card card = new CheapCard(new TokensAmount(2, 0, 0, 0, 0), 0, Green);
        Player player = new Player(new TokensAmount(1, 0, 0, 0, 0, 1));
        Turn turn = new BuyCardTurn(card);
        Game game = builder().add(card).add(player).create();

        player.addCard(card(Green));
        player.addCard(card(Green));

        // when
        turn.invoke(game);

        // then
        assertEquals(new TokensAmount(1, 0, 0, 0, 0, 1), player.getTokensAmount());
        assertEquals(new TokensAmount(), game.getTokensAmount());
    }

    @Test
    public void shouldBuyReservedCard() {
        // given
        Card card = new CheapCard(new TokensAmount(1, 0), 0, Green);
        Turn turn = new BuyCardTurn(card);
        Player player = new Player(new TokensAmount(5));
        Game game = builder().add(player).create();

        player.addCard(card);
        card.setReserved(true);

        // when
        turn.invoke(game);

        // then
        assertFalse(card.isReserved());
        assertEquals(new TokensAmount(), player.getTokensAmount());
        assertEquals(new TokensAmount(0, 5), game.getTokensAmount());
    }

    @Test
    public void shouldThrowOnCardNotInGame() {
        // given
        Turn turn = new BuyCardTurn(new CheapCard());
        Game game = builder().add(new Player()).create();

        expectedException.expect(IllegalTurnException.class);
        expectedException.expectMessage("Card not in model");

        // when
        turn.invoke(game);
    }

    @Test
    public void shouldThrowOnNotEnoughResources() {
        // given
        Card card = new CheapCard(new TokensAmount(2, 0), 0, Green);
        Turn turn = new BuyCardTurn(card);
        Game game = builder().add(card).add(new Player()).create();

        expectedException.expect(IllegalTurnException.class);
        expectedException.expectMessage("Not enough resources");

        // when
        turn.invoke(game);
    }

    private static Card card(TokenColor color) {
        return new CheapCard(new TokensAmount(), 0, color);
    }
}