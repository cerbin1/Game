package app.view;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SizeTest {
    @Test
    public void shouldGetWidth() {
        // given
        int width = 15;
        int height = 25;

        // when
        Size size = new Size(width, height);

        // then
        assertEquals(width, size.getWidth());
        assertEquals(height, size.getHeight());
    }

    @Test
    public void shouldParseSize() {
        // given
        String string = "104x124";

        // when
        Size size = Size.parseSize(string);

        // then
        assertEquals(104, size.getWidth());
        assertEquals(124, size.getHeight());
    }
}