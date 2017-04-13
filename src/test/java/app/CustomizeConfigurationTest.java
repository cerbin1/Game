package app;

import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class CustomizeConfigurationTest {
    @Test
    public void shouldSetPropertiesFromArguments() {
        // given
        CustomizeConfiguration customizeConfiguration = new CustomizeConfiguration(new Properties());

        // when
        customizeConfiguration.setConfiguration("true", "1920", "1080", "false");

        // then
        Properties properties = customizeConfiguration.getPropertiesFromArgs();
        assertEquals("true", properties.getProperty("debug"));
        assertEquals("1920", properties.getProperty("width"));
        assertEquals("1080", properties.getProperty("height"));
        assertEquals("false", properties.getProperty("fullscreen"));
    }

    @Test
    public void shouldNotSetPropertiesFromArguments() {
        // given
        CustomizeConfiguration customizeConfiguration = new CustomizeConfiguration(new Properties());


        // when
        customizeConfiguration.setConfiguration(" ", " ", " ", " ");

        // then
        Properties properties = customizeConfiguration.getPropertiesFromArgs();
        assertEquals(null, properties.getProperty("debug"));
        assertEquals(null, properties.getProperty("width"));
        assertEquals(null, properties.getProperty("height"));
        assertEquals(null, properties.getProperty("fullscreen"));
    }
}