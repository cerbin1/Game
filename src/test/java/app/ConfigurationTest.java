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
        Resolution resolution = Configuration.getSize();
        boolean fullscreen = Configuration.isFullscreen();

        // then
        assertEquals(false, debug);
        assertEquals(1920, resolution.getWidth());
        assertEquals(1080, resolution.getHeight());
        assertEquals(false, fullscreen);
    }

    @Test
    public void shouldLoadConfigurationFromFile() {
        // when
        ConfigurationLoader configurationLoader = new ConfigurationLoader();

        // then
        Configuration.use(configurationLoader);

        assertEquals(false, Configuration.isDebug());
        assertEquals(1920, Configuration.getSize().getWidth());
        assertEquals(1080, Configuration.getSize().getHeight());
        assertEquals(false, Configuration.isFullscreen());
    }

    @Test
    public void shouldSetPropertiesFromArguments() {
        // given
        String[] args = {"debug=true", "size=1366x768", "fullscreen=true"};

        // when
        ConfigurationLoader configurationLoader = new ConfigurationLoader(args);

        // then
        Configuration.use(configurationLoader);

        assertEquals(true, Configuration.isDebug());
        assertEquals(1366, Configuration.getSize().getWidth());
        assertEquals(768, Configuration.getSize().getHeight());
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
        assertEquals(1920, Configuration.getSize().getWidth());
        assertEquals(1080, Configuration.getSize().getHeight());
        assertEquals(false, Configuration.isFullscreen());
    }
}
