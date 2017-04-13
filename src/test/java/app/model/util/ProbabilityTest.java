package app.model.util;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ProbabilityTest {
    @Test
    public void shouldGetInteger() {
        // given
        Random random = mock(Random.class);
        Probability probability = new Probability(random);
        when(random.nextInt(5)).thenReturn(3);

        // when
        int value = probability.nextInt(5, 10);

        // then
        verify(random, times(1)).nextInt(5);
        assertEquals(8, value);
    }

    @Test
    public void shouldGetNegativeInteger() {
        // given
        Random random = mock(Random.class);
        Probability probability = new Probability(random);
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
