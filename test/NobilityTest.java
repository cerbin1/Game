import app.MockRandom;
import app.game.card.nobility.Nobility;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class NobilityTest {
    @Test
    public void shouldDrawRandomPoints() {
        // given
        Random random = new MockRandom(0, 1, 0);
        Nobility nobility = new Nobility(random);

        // when
        int points1 = nobility.getRandomPoints();
        int points2 = nobility.getRandomPoints();

        // then
        assertEquals(points1, 4);
        assertEquals(points2, 3);
    }
}