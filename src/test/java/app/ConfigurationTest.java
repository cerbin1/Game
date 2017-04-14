package app;

import app.config.Configuration;
import app.config.ConfigurationLoader;
import app.view.Resolution;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    @Test
    public void shouldGetConfiguration() {
        // when
        boolean debug = Configuration.isDebug();
        Resolution resolution = Configuration.getResolution();
        boolean fullscreen = Configuration.isFullscreen();

        // then
        assertEquals(false, debug);
        assertEquals(1920, resolution.getWidth());
        assertEquals(1080, resolution.getHeight());
        assertEquals(true, fullscreen);
    }

    @Test
    public void shouldLoadConfigurationFromFile() {
        // when
        ConfigurationLoader configurationLoader = new ConfigurationLoader();

        // then
        Configuration.use(configurationLoader);

        assertEquals(false, Configuration.isDebug());
        assertEquals(1920, Configuration.getResolution().getWidth());
        assertEquals(1080, Configuration.getResolution().getHeight());
        assertEquals(true, Configuration.isFullscreen());
    }

    @Test
    public void shouldSetPropertiesFromArguments() {
        // given
        String[] args = {"debug=true", "resolution=1366x768", "fullscreen=true"};

        // when
        ConfigurationLoader configurationLoader = new ConfigurationLoader(args);

        // then
        Configuration.use(configurationLoader);

        assertEquals(true, Configuration.isDebug());
        assertEquals(1366, Configuration.getResolution().getWidth());
        assertEquals(768, Configuration.getResolution().getHeight());
        assertEquals(true, Configuration.isFullscreen());
    }

    @Test
    public void shouldNotSetPropertiesFromArgumentsAndLetDefaultValues() {
        // given
        String[] args = {" ", " ", " ", " "};

        // when
        ConfigurationLoader configurationLoader = new ConfigurationLoader(args);

        // then
        Configuration.use(configurationLoader);

        assertEquals(false, Configuration.isDebug());
        assertEquals(1920, Configuration.getResolution().getWidth());
        assertEquals(1080, Configuration.getResolution().getHeight());
        assertEquals(true, Configuration.isFullscreen());
    }
}
