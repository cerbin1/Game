package app.game.card;

import app.game.token.Tokens;
import app.util.Probability;
import org.junit.Test;

import static app.game.token.TokenColor.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CardFactoryTest {
    @Test
    public void shouldCreateCheapCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(3, 0, 0, 0, 1, 3);
        CardFactory factory = new CardFactory(probability);

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
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(5, 0, 1, 1, 2, 4, 3, 1);
        CardFactory factory = new CardFactory(probability);

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
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(10, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 4, 0);
        CardFactory factory = new CardFactory(probability);

        // when
        Card expensiveCard = factory.createExpensiveCard();

        // then
        assertEquals(new Tokens(2, 2, 2, 2, 2), expensiveCard.getCost());
        assertEquals(4, expensiveCard.getPoints());
        assertEquals(Green, expensiveCard.getColor());
    }
}
