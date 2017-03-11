package app.game.card;

import app.game.token.Tokens;
import org.junit.Test;
import testUtils.MockProbability;

import static app.game.token.TokenColor.*;
import static org.junit.Assert.assertEquals;

public class CardFactoryTest {
    @Test
    public void shouldCreateCheapCard() {
        // given
        CardFactory factory = new CardFactory(new MockProbability(3, 0, 0, 0, 1, 3));

        // when
        Card cheapCard = factory.createCheapCard();

        // then
        assertEquals(new Tokens(3, 0, 0, 0, 0), cheapCard.getCost());
        assertEquals(1, cheapCard.getPoints());
        assertEquals(Black, cheapCard.getColor());
    }

    @Test
    public void shouldCreateMediumCard() {
        // given
        CardFactory factory = new CardFactory(new MockProbability(5, 0, 1, 1, 2, 4, 3, 1));

        // when
        Card mediumCard = factory.createMediumCard();

        // then
        assertEquals(new Tokens(1, 2, 1, 0, 1), mediumCard.getCost());
        assertEquals(3, mediumCard.getPoints());
        assertEquals(Purple, mediumCard.getColor());
    }

    @Test
    public void shouldCreateExpensiveCard() {
        // given
        CardFactory builder = new CardFactory(new MockProbability(10, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 4, 0));

        // when
        Card expensiveCard = builder.createExpensiveCard();

        // then
        assertEquals(new Tokens(2, 2, 2, 2, 2), expensiveCard.getCost());
        assertEquals(4, expensiveCard.getPoints());
        assertEquals(Green, expensiveCard.getColor());
    }
}
