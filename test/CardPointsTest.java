import app.game.card.CardPoints;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardPointsTest {
    @Test
    public void shouldGenerateCheapCardPoints() {
        // given
        CardPoints points = new CardPoints(new MockProbability(14));

        // when
        int cheapCardPoints = points.generateCheapCardPoints();

        // then
        assertEquals(14, cheapCardPoints);
    }

    @Test
    public void shouldGenerateMediumCardPoints() {
        // given
        CardPoints points = new CardPoints(new MockProbability(18));

        // when
        int mediumCardPoints = points.generateMediumCardPoints();

        // then
        assertEquals(18, mediumCardPoints);
    }

    @Test
    public void shouldGenerateExpensiveCardPoints() {
        // given
        CardPoints points = new CardPoints(new MockProbability(87));

        // when
        int expensiveCardPoints = points.generateExpensiveCardPoints();

        // then
        assertEquals(87, expensiveCardPoints);
    }
}
