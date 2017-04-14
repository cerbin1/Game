package app.config;

import java.util.Properties;

public class Configuration {

    private static final Properties properties = new ConfigurationLoader().getProperties();

    public static boolean isDebug() {
        return Boolean.parseBoolean(properties.getProperty("debug"));
    }

    public static int getWidth() {
        return Integer.parseInt(properties.getProperty("width"));
    }

    public static int getHeight() {
        return Integer.parseInt(properties.getProperty("height"));
    }

    public static boolean isFullscreen() {
        return Boolean.parseBoolean(properties.getProperty("fullscreen"));
    }
}
