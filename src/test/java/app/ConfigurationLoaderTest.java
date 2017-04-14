package app;

import app.config.ConfigurationLoader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigurationLoaderTest {
    @Test
    public void shouldLoadConfigurationFromFile() {
        // given
        ConfigurationLoader configurationLoader = new ConfigurationLoader();

        // when
        configurationLoader.getProperties();

        // then
        assertEquals("false", configurationLoader.getProperty("debug"));
        assertEquals("1920x1080", configurationLoader.getProperty("size"));
        assertEquals("false", configurationLoader.getProperty("fullscreen"));
    }

    @Test
    public void shouldSetPropertiesFromArguments() {
        // given
        String[] args = {"debug=true", "size=1210x14", "fullscreen=false"};

        // when
        ConfigurationLoader configurationLoader = new ConfigurationLoader(args);

        // then
        assertEquals("true", configurationLoader.getProperty("debug"));
        assertEquals("1210x14", configurationLoader.getProperty("size"));
        assertEquals("true", configurationLoader.getProperty("fullscreen"));
    }

    @Test
    public void shouldNotSetPropertiesFromArgumentsAndLetDefaultValues() {
        // given
        String[] args = {" ", " ", " ", " "};

        // when
        ConfigurationLoader configurationLoader = new ConfigurationLoader(args);

        // then
        assertEquals("false", configurationLoader.getProperty("debug"));
        assertEquals("1920x1080", configurationLoader.getProperty("size"));
        assertEquals("false", configurationLoader.getProperty("fullscreen"));
    }
}
