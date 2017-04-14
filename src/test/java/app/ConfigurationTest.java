package app;

import app.config.Configuration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    @Test
    public void shouldGetConfiguration() {
        // when
        boolean debug = Configuration.isDebug();
        int width = Configuration.getWidth();
        int height = Configuration.getHeight();
        boolean fullscreen = Configuration.isFullscreen();

        // then
        assertEquals(false, debug);
        assertEquals(1920, width);
        assertEquals(1080, height);
        assertEquals(true, fullscreen);
    }
}
