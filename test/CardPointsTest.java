import app.game.card.CardPoints;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardPointsTest {
    @Test
    public void shouldGenerateCheapCardPoints() {
        // given
        CardPoints points = new CardPoints(new MockProbability(14));

        // when
        int cheapCardPoints = points.getCheap();

        // then
        assertEquals(14, cheapCardPoints);
    }

    @Test
    public void shouldGenerateMediumCardPoints() {
        // given
        CardPoints points = new CardPoints(new MockProbability(18));

        // when
        int mediumCardPoints = points.getMedium();

        // then
        assertEquals(18, mediumCardPoints);
    }

    @Test
    public void shouldGenerateExpensiveCardPoints() {
        // given
        CardPoints points = new CardPoints(new MockProbability(87));

        // when
        int expensiveCardPoints = points.getExpensive();

        // then
        assertEquals(87, expensiveCardPoints);
    }
}
