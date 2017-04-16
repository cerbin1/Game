package app.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

public class ConfigurationLoader {
    private final ConfigurationFile configurationFile;

    private final Properties properties;
    private final List[] possibleValues = {
            asList("true", "false"), asList("1920x1080", "1280x720", "1366x768", "1600x900"), asList("true", "false")};
    private final Map<String, List> propertiesMap = initializeMap();

    private Map<String, List> initializeMap() {
        Map<String, List> map = new HashMap<>();
        map.put("debug", possibleValues[0]);
        map.put("resolution", possibleValues[1]);
        map.put("fullscreen", possibleValues[2]);
        return map;
    }

    public ConfigurationLoader(ConfigurationFile configurationFile, String... args) {
        this(configurationFile);
        this.applyConfigurationFromArgs(args);
    }

    public ConfigurationLoader(ConfigurationFile configurationFile) {
        this.configurationFile = configurationFile;
        this.properties = getProperties();
    }

    private Properties getProperties() {
        if (configurationFile.exists()) {
            return loadExistingConfiguration();
        } else {
            return createNewConfigurationFile();
        }
    }

    private Properties loadExistingConfiguration() {
        try {
            InputStream inputStream = configurationFile.getInputStream();
            Properties properties = getDefaultProperties();
            properties.load(inputStream);
            inputStream.close();
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Could not load configuration", e);
        }
    }

    private Properties createNewConfigurationFile() {
        try {
            OutputStream outputStream = configurationFile.getOutputStream();
            Properties properties = getDefaultProperties();
            properties.store(outputStream, null);
            outputStream.close();
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

    private static Properties getDefaultProperties() {
        Properties properties = new Properties();
        properties.setProperty("debug", "false");
        properties.setProperty("resolution", "1920x1080");
        properties.setProperty("fullscreen", "true");
        return properties;
    }
}
