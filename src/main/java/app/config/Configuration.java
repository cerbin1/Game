package app.config;

import app.view.Size;

public class Configuration {
    private static ConfigurationLoader properties = new ConfigurationLoader();

    public static void use(ConfigurationLoader configurationLoader) {
        properties = configurationLoader;
    }

    public static boolean isDebug() {
        return Boolean.parseBoolean(properties.getProperty("debug"));
    }

    public static Size getSize() {
        return Size.parseSize(properties.getProperty("size"));
    }

    public static boolean isFullscreen() {
        return Boolean.parseBoolean(properties.getProperty("fullscreen"));
    }
}
