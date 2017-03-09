import app.game.card.CardPoints;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardPointsTest {
    @Test
    public void shouldGenerateCheapCardPoints() {
        // given
        CardPoints points = new CardPoints(new MockProbability(1));

        // when
        int cheapCardPoints = points.getCheap();

        // then
        assertEquals(1, cheapCardPoints);
    }

    @Test
    public void shouldGenerateMediumCardPoints() {
        // given
        CardPoints points = new CardPoints(new MockProbability(3));

        // when
        int mediumCardPoints = points.getMedium();

        // then
        assertEquals(3, mediumCardPoints);
    }

    @Test
    public void shouldGenerateExpensiveCardPoints() {
        // given
        CardPoints points = new CardPoints(new MockProbability(5));

        // when
        int expensiveCardPoints = points.getExpensive();

        // then
        assertEquals(5, expensiveCardPoints);
    }
}
