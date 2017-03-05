import app.game.card.nobility.NobilityFactory;
import app.util.Random;
import org.junit.Test;

import static org.junit.Assert.*;

public class NobilityFactoryTest {
    @Test
    public void shouldDrawRandomPoints() {
        // given
        Random random = new MockRandom(3, 4);
        NobilityFactory generator = new NobilityFactory(random);

        // when
        int points1 = generator.generateRandomPoints();
        int points2 = generator.generateRandomPoints();

        // then
        assertEquals(points1, 3);
        assertEquals(points2, 4);
    }
}