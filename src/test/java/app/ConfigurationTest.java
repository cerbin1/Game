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
        assertEquals(false, debug);
        assertEquals(1920, width);
        assertEquals(1080, height);
        assertEquals(true, fullscreen);
    }

    @Test
    public void shouldAcceptStringArguments() {
        // given
        String[] args = {"debug=true", "fullscreen=false", "size=1280x720"};

        // when
        ConfigurationLoader.acceptStringParams(args);

        // then
        assertEquals(true, Configuration.getDebug());
        assertEquals(1280, Configuration.getWidth());
        assertEquals(720, Configuration.getHeight());
        assertEquals(false, Configuration.getFullscreen());
    }

}
