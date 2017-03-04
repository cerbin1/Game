import app.util.Random;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RandomTest {
    @Test
    public void shouldGetInteger() {
        // given
        Random random = new Random(new MockJavaRandom(3));

        // when
        int value = random.nextInt(5, 10);

        // then
        assertEquals(8, value);
    }

    @Test
    public void shouldGetNegativeInteger() {
        // given
        Random random = new Random(new MockJavaRandom(3));

        // when
        int value = random.nextInt(-5, 5);

        // then
        assertEquals(-2, value);
    }

    @Test
    public void shouldThrowOnInvalidBound() {
        // given
        Random random = new Random();

        // when
        try {
            random.nextInt(1, 0);
            assertTrue(false);
        }
        // then
        catch (IllegalArgumentException exception) {
            assertTrue(true);
        }
    }
}
