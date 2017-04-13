package app;

import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class ConfigurationLoaderTest {
    @Test
    public void shouldLoadDefaultConfiguration() {
        // given
        ConfigurationLoader configurationLoader = new ConfigurationLoader();

        // when
        configurationLoader.loadDefaultConfiguration();

        // then
        Properties properties = configurationLoader.getProperties();
        assertEquals("false", properties.getProperty("debug"));
        assertEquals("1920", properties.getProperty("width"));
        assertEquals("1080", properties.getProperty("height"));
        assertEquals("true", properties.getProperty("fullscreen"));
    }

    @Test
    public void shouldLoadConfigurationFromFile() {
        // given
        ConfigurationLoader configurationLoader = new ConfigurationLoader();

        // when
        configurationLoader.setConfiguration();

        // then
        Properties properties = configurationLoader.getProperties();
        assertEquals("true", properties.getProperty("debug"));
        assertEquals("1920", properties.getProperty("width"));
        assertEquals("1080", properties.getProperty("height"));
        assertEquals("true", properties.getProperty("fullscreen"));
    }
}