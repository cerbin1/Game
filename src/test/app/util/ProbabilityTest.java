package app.util;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProbabilityTest {
    @Test
    public void shouldGetInteger() {
        // given
        Probability probability = new Probability();
        Random random = mock(Random.class);
        when(random.nextInt(5)).thenReturn(3);

        // when
        int value = probability.nextInt(5, 10);

        // then
        assertEquals(8, value);
    }

    @Test
    public void shouldGetNegativeInteger() {
        // given
        Probability probability = new Probability();
        Random random = mock(Random.class);
        when(random.nextInt(10)).thenReturn(3);

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
