package app.model;

import app.model.card.Card;
import app.model.card.CheapCard;
import app.model.card.ExpensiveCard;
import app.model.card.MediumCard;
import app.model.card.nobility.Nobility;
import app.model.token.Resources;
import app.model.token.TokensAmount;
import org.junit.Test;

import static app.model.token.TokenColor.Green;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void shouldCreatePlayer() {
        // when
        Player player = new Player(new TokensAmount(0, 1, 2, 3, 4));

        // then
        assertEquals(new TokensAmount(0, 1, 2, 3, 4, 0), player.getTokensAmount());
    }

    @Test
    public void shouldNotCountPointsFromReservedCards() {
        // given
        Player player = new Player();
        Card reservedCard1 = new CheapCard(new TokensAmount(), 1, Green);
        Card reservedCard2 = new MediumCard(new TokensAmount(), 2, Green);
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
        player.addCard(new MediumCard(new TokensAmount(), 4, Green));
        player.addCard(new ExpensiveCard(new TokensAmount(), 5, Green));
        player.addNobility(new Nobility(new TokensAmount(), 4));
        player.addNobility(new Nobility(new TokensAmount(), 3));

        // when
        int points = player.getPoints();

        // then
        assertEquals(16, points);
    }

    @Test
    public void shouldSetTokens() {
        // given
        Player player = new Player();
        TokensAmount tokensAmount = new TokensAmount(1, 2, 3, 4, 5);

        // when
        player.setTokensAmount(tokensAmount);

        // then
        assertEquals(tokensAmount, player.getTokensAmount());
    }

    @Test
    public void shouldNotIncludeReservedCardToPlayerResources() {
        // given
        Player player = new Player();
        Card card = new CheapCard(new TokensAmount(), 0, Green);
        card.setReserved(true);
        player.addCard(card);
        player.addCard(new CheapCard(new TokensAmount(), 0, Green));

        // when
        Resources resources = player.getResources();

        // then
        Resources expected = new Resources(new TokensAmount(1, 0, 0, 0, 0), new TokensAmount());
        assertEquals(expected, resources);
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
        Player player = new Player(new TokensAmount(0, 2));

        // when
        player.incVersatile();

        // then
        assertEquals(new TokensAmount(0, 3), player.getTokensAmount());
    }
}
