package app.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static java.util.Arrays.asList;

public class ConfigurationLoader {
    private final Properties properties;

    private final List<String> propertyKeys = asList("debug", "size", "fullscreen");
    private final List[] possibleValues = {
            asList("true", "false"), asList("1920x1080", "1280x720"), asList("true", "false")};

    public ConfigurationLoader(String[] args) {
        properties = getProperties();
        applyConfigurationFromArgs(args);
    }

    public ConfigurationLoader() {
        properties = getProperties();
    }

    public Properties getProperties() {
        File config = new File("config.properties");
        if (config.exists()) {
            return loadExistingConfiguration();
        } else {
            return createNewConfigurationFile();
        }
    }

    private Properties loadExistingConfiguration() {
        try {
            FileInputStream in = new FileInputStream("config.properties");
            Properties properties = new Properties();
            properties.load(in);
            in.close();
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Could not load configuration", e);
        }
    }

    private Properties createNewConfigurationFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("config.properties");
            Properties properties = new Properties();
            properties.setProperty("debug", "true");
            properties.setProperty("width", "1920");
            properties.setProperty("height", "1080");
            properties.setProperty("fullscreen", "true");
            properties.store(fileOutputStream, null);
            fileOutputStream.close();
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Could not create configuration", e);
        }
    }

    private void applyConfigurationFromArgs(String... args) {
        // TODO implement
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}
