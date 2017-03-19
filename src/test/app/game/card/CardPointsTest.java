package app.game.card;

import app.util.Probability;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CardPointsTest {
    @Test
    public void shouldGenerateCheapCardPoints() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(1);
        CardPoints points = new CardPoints(probability);

        // when
        int cheapCardPoints = points.getCheap();

        // then
        assertEquals(1, cheapCardPoints);
    }

    @Test
    public void shouldGenerateMediumCardPoints() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(12);
        CardPoints points = new CardPoints(probability);

        // when
        int mediumCardPoints = points.getMedium();

        // then
        assertEquals(3, mediumCardPoints);
    }

    @Test
    public void shouldGenerateExpensiveCardPoints() {
        // given
        Probability probability = mock(Probability.class);
        when(probability.nextInt(3, 5)).thenReturn(124125);
        CardPoints points = new CardPoints(probability);

        // when
        int expensiveCardPoints = points.getExpensive();

        // then
        assertEquals(5, expensiveCardPoints);
    }
}
