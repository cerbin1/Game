import app.game.card.nobility.NobilityPointsGenerator;
import app.util.Random;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NobilityPointsGeneratorTest {
    @Test
    public void shouldDrawRandomPoints() {
        // given
        Random random = new MockRandom(3, 4);
        NobilityPointsGenerator generator = new NobilityPointsGenerator(random);

        // when
        int points1 = generator.generateRandomPoints();
        int points2 = generator.generateRandomPoints();

        // then
        assertEquals(points1, 3);
        assertEquals(points2, 4);
    }
}