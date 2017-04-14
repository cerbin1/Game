package app;

import app.config.Configuration;
import app.config.ConfigurationLoader;
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
        String[] args = {"debug=true", "size=1210x14", "fullscreen=false"};

        // when
        ConfigurationLoader configurationLoader = new ConfigurationLoader(args);

        // then
        Configuration.use(configurationLoader);

        assertEquals(true, Configuration.isDebug());
        assertEquals(1210, Configuration.getSize().getWidth());
        assertEquals(14, Configuration.getSize().getHeight());
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
