package app.config;

import app.view.Resolution;

public class Configuration {
    private static ConfigurationLoader properties = new ConfigurationLoader();

    public static void use(ConfigurationLoader configurationLoader) {
        properties = configurationLoader;
    }

    public static boolean isDebug() {
        return Boolean.parseBoolean(properties.getProperty("debug"));
    }

    public static Resolution getSize() {
        return Resolution.parseSize(properties.getProperty("size"));
    }

    public static boolean isFullscreen() {
        return Boolean.parseBoolean(properties.getProperty("fullscreen"));
    }
}
