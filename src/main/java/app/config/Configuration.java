package app.config;

import app.view.Resolution;

import java.io.File;

public class Configuration {
    private static ConfigurationLoader properties = new ConfigurationLoader(new FileConfigurationFile(new File("config.properties")));

    public static void use(ConfigurationLoader configurationLoader) {
        properties = configurationLoader;
    }

    public static boolean isDebug() {
        return Boolean.parseBoolean(properties.getProperty("debug"));
    }

    public static Resolution getResolution() {
        return Resolution.parseResolution(properties.getProperty("resolution"));
    }

    public static boolean isFullscreen() {
        return Boolean.parseBoolean(properties.getProperty("fullscreen"));
    }
}
