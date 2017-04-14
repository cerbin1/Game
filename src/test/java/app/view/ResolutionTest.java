package app.view;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResolutionTest {
    @Test
    public void shouldGetWidth() {
        // given
        int width = 15;
        int height = 25;

        // when
        Resolution resolution = new Resolution(width, height);

        // then
        assertEquals(width, resolution.getWidth());
        assertEquals(height, resolution.getHeight());
    }

    @Test
    public void shouldParseSize() {
        // given
        String string = "104x124";

        // when
        Resolution resolution = Resolution.parseSize(string);

        // then
        assertEquals(104, resolution.getWidth());
        assertEquals(124, resolution.getHeight());
    }
}