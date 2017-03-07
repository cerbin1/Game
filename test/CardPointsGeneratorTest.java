import app.game.card.CardPointsGenerator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardPointsGeneratorTest {
    @Test
    public void shouldGenerateCheapCardPoints() {
        // given
        CardPointsGenerator generator = new CardPointsGenerator(new MockRandom(14));

        // when
        int cheapCardPoints = generator.generateCheapCardPoints();

        // then
        assertEquals(14, cheapCardPoints);
    }

    @Test
    public void shouldGenerateMediumCardPoints() {
        // given
        CardPointsGenerator generator = new CardPointsGenerator(new MockRandom(18));

        // when
        int mediumCardPoints = generator.generateMediumCardPoints();

        // then
        assertEquals(18, mediumCardPoints);
    }

    @Test
    public void shouldGenerateExpensiveCardPoints() {
        // given
        CardPointsGenerator generator = new CardPointsGenerator(new MockRandom(87));

        // when
        int expensiveCardPoints = generator.generateExpensiveCardPoints();

        // then
        assertEquals(87, expensiveCardPoints);
    }
}
