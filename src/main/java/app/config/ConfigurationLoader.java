package app.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {
    private Properties properties = new Properties();

    ConfigurationLoader() {
        setConfiguration();
    }

    public void setConfiguration() {
        File config = new File("config.properties");
        if (config.exists()) {
            loadExistingConfiguration();
        } else {
            createNewConfigurationFile();
        }
    }

    private void createNewConfigurationFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("config.properties");
            properties = new Properties();
            properties.setProperty("debug", "true");
            properties.setProperty("width", "1920");
            properties.setProperty("height", "1080");
            properties.setProperty("fullscreen", "true");
            properties.store(fileOutputStream, null);
            fileOutputStream.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private void loadExistingConfiguration() {
        try {
            FileInputStream in = new FileInputStream("config.properties");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfigurationFromArguments(String... args) {
        new CustomizeConfiguration(properties, args);
    }

    public Properties getProperties() {
        return properties;
    }
}
