package app.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

public class ConfigurationLoader {
    private final Properties properties;
    private final Map<String, List> propertiesMap = initializeMap();
    private final List[] possibleValues = {
            asList("true", "false"), asList("1920x1080", "1280x720", "1366x768", "1600x900"), asList("true", "false")};

    private Map<String, List> initializeMap() {
        Map<String, List> map = new HashMap<>();
        map.put("debug", possibleValues[0]);
        map.put("size", possibleValues[1]);
        map.put("fullscreen", possibleValues[2]);
        return map;
    }

    public ConfigurationLoader(String... args) {
        properties = getProperties();
        applyConfigurationFromArgs(args);
    }

    public ConfigurationLoader() {
        properties = getProperties();
    }

    private Properties getProperties() {
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
            properties.setProperty("size", "1920x1080");
            properties.setProperty("fullscreen", "true");
            properties.store(fileOutputStream, null);
            fileOutputStream.close();
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Could not create configuration", e);
        }
    }

    private void applyConfigurationFromArgs(String... args) {
        for (String string : args) {
            String key, value;
            Matcher matcher = Pattern.compile("([a-zA-Z]+)=([a-zA-Z0-9]+)").matcher(string);
            if (matcher.find()) {
                key = matcher.group(1);
                value = matcher.group(2);
                if (propertiesMap.containsKey(key) && propertiesMap.get(key).contains(value)) {
                    properties.setProperty(key, value);
                }
            }
        }
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}
