package app.game;

import app.game.card.Card;
import app.game.card.CheapCard;
import app.game.card.ExpensiveCard;
import app.game.card.MediumCard;
import app.game.card.nobility.Nobility;
import app.game.token.Tokens;
import org.junit.Test;

import static app.game.token.TokenColor.Green;
import static app.game.token.TokenColor.Red;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void shouldCreatePlayer() {
        // when
        Player player = new Player(new Tokens(0, 1, 2, 3, 4));

        // then
        assertEquals(new Tokens(0, 1, 2, 3, 4, 0), player.getTokens());
    }

    @Test
    public void shouldNotCountPointsFromReservedCards() {
        // given
        Player player = new Player();
        Card reservedCard1 = new CheapCard(new Tokens(), 1, Green);
        Card reservedCard2 = new MediumCard(new Tokens(), 2, Green);
        reservedCard1.setReserved(true);
        reservedCard2.setReserved(true);
        player.addCard(reservedCard1);
        player.addCard(reservedCard2);

        // when
        int points = player.getPoints();

        // then
        assertEquals(0, points);
    }

    @Test
    public void shouldCountPoints() {
        // given
        Player player = new Player();
        player.addCard(new MediumCard(new Tokens(), 4, Green));
        player.addCard(new ExpensiveCard(new Tokens(), 5, Green));
        player.addNobility(new Nobility(new Tokens(), 4));
        player.addNobility(new Nobility(new Tokens(), 3));

        // when
        int points = player.getPoints();

        // then
        assertEquals(16, points);
    }

    @Test
    public void shouldSetTokens() {
        // given
        Player player = new Player();
        Tokens tokens = new Tokens(1, 2, 3, 4, 5);

        // when
        player.setTokens(tokens);

        // then
        assertEquals(tokens, player.getTokens());
    }

    @Test
    public void shouldGetResources() {
        // given
        Player player = new Player(new Tokens(1, 1, 1, 1, 1, 1));
        player.addCard(new CheapCard(new Tokens(), 0, Red));
        player.addCard(new CheapCard());

        // when
        Tokens resources = player.getResources();

        // then
        assertEquals(new Tokens(2, 1, 1, 1, 2, 1), resources);
    }

    @Test
    public void shouldNotIncludeReservedCardToPlayerResources() {
        // given
        Player player = new Player();
        Card card = new CheapCard(new Tokens(), 0, Green);
        card.setReserved(true);
        player.addCard(card);
        player.addCard(new CheapCard(new Tokens(), 0, Green));

        // when
        Tokens tokens = player.getResources();

        // then
        assertEquals(new Tokens(1, 0, 0, 0, 0), tokens);
    }

    @Test
    public void shouldHaveCard() {
        // given
        Player player = new Player();
        Card card = new CheapCard();
        player.addCard(card);

        // when
        boolean hasCard = player.hasCard(card);

        // then
        assertTrue(hasCard);
    }

    @Test
    public void shouldNotHaveCard() {
        // given
        Player player = new Player();
        Card card = new CheapCard();

        // when
        boolean hasCard = player.hasCard(card);

        // then
        assertFalse(hasCard);
    }

    @Test
    public void shouldIncrementVersatile() {
        // given
        Player player = new Player(new Tokens(0, 2));

        // when
        player.incVersatile(1);

        // then
        assertEquals(new Tokens(0, 3), player.getTokens());
    }
}
