package app;

import app.config.Configuration;
import app.view.Size;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    @Test
    public void shouldGetConfiguration() {
        // when
        boolean debug = Configuration.isDebug();
        Size size = Configuration.getSize();
        boolean fullscreen = Configuration.isFullscreen();

        // then
        assertEquals(false, debug);
        assertEquals(1920, size.getWidth());
        assertEquals(1080, size.getHeight());
        assertEquals(false, fullscreen);
    }
}
