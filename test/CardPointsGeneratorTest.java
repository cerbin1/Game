import app.game.card.CardPointsGenerator;
import app.util.Random;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardPointsGeneratorTest {

    @Test
    public void shouldGenerateCheapCardPoints() {
        // given
        Random random = new MockRandom(0, 1);
        CardPointsGenerator generator = new CardPointsGenerator(random);

        // when
        int cheapCardPoints1 = generator.generateCheapCardPoints();
        int cheapCardPoints2 = generator.generateCheapCardPoints();

        // then
        assertEquals(cheapCardPoints1, 0);
        assertEquals(cheapCardPoints2, 1);
    }

    @Test
    public void shouldGenerateMediumCardPoints() {
        // given
        Random random = new MockRandom(1, 2, 3);
        CardPointsGenerator generator = new CardPointsGenerator(random);

        // when
        int mediumCardPoints1 = generator.generateMediumCardPoints();
        int mediumCardPoints2 = generator.generateMediumCardPoints();
        int mediumCardPoints3 = generator.generateMediumCardPoints();

        // then
        assertEquals(mediumCardPoints1, 1);
        assertEquals(mediumCardPoints2, 2);
        assertEquals(mediumCardPoints3, 3);
    }

    @Test
    public void shouldGenerateExpensiveCardPoints() {
        // given
        Random random = new MockRandom(3, 4, 5);
        CardPointsGenerator generator = new CardPointsGenerator(random);

        // when
        int expensiveCardPoints1 = generator.generateExpensiveCardPoints();
        int expensiveCardPoints2 = generator.generateExpensiveCardPoints();
        int expensiveCardPoints3 = generator.generateExpensiveCardPoints();

        // then
        assertEquals(expensiveCardPoints1, 3);
        assertEquals(expensiveCardPoints2, 4);
        assertEquals(expensiveCardPoints3, 5);
    }
}