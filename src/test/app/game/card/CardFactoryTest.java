package app.game.card;

import app.game.token.Tokens;
import app.util.Probability;
import org.junit.Test;

import static app.game.token.TokenColor.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CardFactoryTest {
    @Test
    public void shouldCreateCheapCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(3);
        when(probability.nextInt(0, 5)).thenReturn(0, 0, 0, 3);
        when(probability.nextInt(0, 1)).thenReturn(1);
        CardFactory factory = new CardFactory(probability);

        // when
        Card cheapCard = factory.createCheapCard();

        // then
        verify(probability, times(1)).nextInt(3, 5);
        verify(probability, times(4)).nextInt(0, 5);
        verify(probability, times(1)).nextInt(0, 1);
        assertEquals(new Tokens(3, 0, 0, 0, 0), cheapCard.getCost());
        assertEquals(1, cheapCard.getPoints());
        assertEquals(Black, cheapCard.getColor());
    }

    @Test
    public void shouldCreateMediumCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(5, 8)).thenReturn(5);
        when(probability.nextInt(1, 3)).thenReturn(3);
        when(probability.nextInt(0, 5)).thenReturn(0, 1, 1, 2, 4, 1);
        CardFactory factory = new CardFactory(probability);

        // when
        Card mediumCard = factory.createMediumCard();

        // then
        verify(probability, times(1)).nextInt(5, 8);
        verify(probability, times(6)).nextInt(0, 5);
        verify(probability, times(1)).nextInt(1, 3);
        assertEquals(new Tokens(1, 2, 1, 0, 1), mediumCard.getCost());
        assertEquals(3, mediumCard.getPoints());
        assertEquals(Purple, mediumCard.getColor());
    }

    @Test
    public void shouldCreateExpensiveCard() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(7, 14)).thenReturn(10);
        when(probability.nextInt(3, 5)).thenReturn(4);
        when(probability.nextInt(0, 5)).thenReturn(0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 0);
        CardFactory factory = new CardFactory(probability);

        // when
        Card expensiveCard = factory.createExpensiveCard();

        // then
        verify(probability, times(1)).nextInt(7, 14);
        verify(probability, times(11)).nextInt(0, 5);
        verify(probability, times(1)).nextInt(3, 5);
        assertEquals(new Tokens(2, 2, 2, 2, 2), expensiveCard.getCost());
        assertEquals(4, expensiveCard.getPoints());
        assertEquals(Green, expensiveCard.getColor());
    }
}
