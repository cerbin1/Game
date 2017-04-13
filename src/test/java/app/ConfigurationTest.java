package app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    @Test
    public void shouldGetConfiguration() {
        // when
        boolean debug = Configuration.getDebug();
        int width = Configuration.getWidth();
        int height = Configuration.getHeight();
        boolean fullscreen = Configuration.getFullscreen();

        // then
        assertEquals(true, debug);
        assertEquals(1920, width);
        assertEquals(1080, height);
        assertEquals(true, fullscreen);
    }
}
