package app.game.card;

import app.util.Probability;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CardPointsTest {
    @Test
    public void shouldGenerateCheapCardPoints() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(0, 1)).thenReturn(1);
        CardPoints points = new CardPoints(probability);

        // when
        int cheapCardPoints = points.getCheap();

        // then
        verify(probability, times(1)).nextInt(0, 1);
        assertEquals(1, cheapCardPoints);
    }

    @Test
    public void shouldGenerateMediumCardPoints() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(1, 3)).thenReturn(3);
        CardPoints points = new CardPoints(probability);

        // when
        int mediumCardPoints = points.getMedium();

        // then
        verify(probability, times(1)).nextInt(1, 3);
        assertEquals(3, mediumCardPoints);
    }

    @Test
    public void shouldGenerateExpensiveCardPoints() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(5);
        CardPoints points = new CardPoints(probability);

        // when
        int expensiveCardPoints = points.getExpensive();

        // then
        verify(probability, times(1)).nextInt(3, 5);
        assertEquals(5, expensiveCardPoints);
    }
}
