package app.view;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class ResolutionTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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
        Resolution resolution = Resolution.parseResolution(string);

        // then
        assertEquals(104, resolution.getWidth());
        assertEquals(124, resolution.getHeight());
    }

    @Test
    public void shouldThrowOnGivingNotNumber() {
        // given
        String string = "120x45dd";

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid resolution");

        // when
        Resolution.parseResolution(string);
    }

    @Test
    public void shouldThrowOnGivingNotEnoughNumbers() {
        // given
        String string = "1920";

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid resolution");

        // when
        Resolution.parseResolution(string);
    }
}