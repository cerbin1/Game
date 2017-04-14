package app;

import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class ConfigurationLoaderTest {
    @Test
    public void shouldLoadConfigurationFromFile() {
        // given
        ConfigurationLoader configurationLoader = new ConfigurationLoader();

        // when
        configurationLoader.setConfiguration();

        // then
        Properties properties = configurationLoader.getProperties();
        assertEquals("false", properties.getProperty("debug"));
        assertEquals("1920", properties.getProperty("width"));
        assertEquals("1080", properties.getProperty("height"));
        assertEquals("false", properties.getProperty("fullscreen"));
    }

    @Test
    public void shouldSetPropertiesFromArguments() {
        // given
        ConfigurationLoader configurationLoader = new ConfigurationLoader();

        // when
        configurationLoader.loadConfigurationFromArguments("false", "1920", "1080", "false");

        // then
        Properties properties = configurationLoader.getProperties();
        assertEquals("false", properties.getProperty("debug"));
        assertEquals("1920", properties.getProperty("width"));
        assertEquals("1080", properties.getProperty("height"));
        assertEquals("false", properties.getProperty("fullscreen"));
    }


    @Test
    public void shouldNotSetPropertiesFromArgumentsAndLetDefaultValues() {
        // given
        ConfigurationLoader configurationLoader = new ConfigurationLoader();

        // when
        configurationLoader.loadConfigurationFromArguments(" ", " ", " ", " ");

        // then
        Properties properties = configurationLoader.getProperties();
        assertEquals("false", properties.getProperty("debug"));
        assertEquals("1920", properties.getProperty("width"));
        assertEquals("1080", properties.getProperty("height"));
        assertEquals("false", properties.getProperty("fullscreen"));
    }
}
