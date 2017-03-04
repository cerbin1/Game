package app.util;

import app.MockRandom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RandomTest {
    @Test
    public void shouldGetInteger() {
        // given
        MockRandom mock = new MockRandom(3);
        Random random = new Random(mock);

        // when
        int value = random.nextInt(5, 10);

        // then
        assertEquals(8, value);
    }

    @Test
    public void shouldGetNegativeInteger() {
        // given
        MockRandom mock = new MockRandom(3);
        Random random = new Random(mock);

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
