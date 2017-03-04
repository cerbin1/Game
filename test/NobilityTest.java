import app.game.card.nobility.Nobility;
import app.util.Random;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NobilityTest {
    @Test
    public void shouldDrawRandomPoints() {
        // given
        Random random = new MockRandom(0, 0, 1);
        Nobility nobility = new Nobility(random);

        // when
        int points1 = nobility.getRandomPoints();
        int points2 = nobility.getRandomPoints();

        // then
        assertEquals(points1, 3);
        assertEquals(points2, 4);
    }
}