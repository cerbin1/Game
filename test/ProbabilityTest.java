import app.util.Probability;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProbabilityTest {
    @Test
    public void shouldGetInteger() {
        // given
        Probability probability = new Probability(new MockJavaRandom(3));

        // when
        int value = probability.nextInt(5, 10);

        // then
        assertEquals(8, value);
    }

    @Test
    public void shouldGetNegativeInteger() {
        // given
        Probability probability = new Probability(new MockJavaRandom(3));

        // when
        int value = probability.nextInt(-5, 5);

        // then
        assertEquals(-2, value);
    }

    @Test
    public void shouldThrowOnInvalidBound() {
        // given
        Probability probability = new Probability();

        // when
        try {
            probability.nextInt(1, 0);
            assertTrue(false);
        }
        // then
        catch (IllegalArgumentException exception) {
            assertTrue(true);
        }
    }
}
