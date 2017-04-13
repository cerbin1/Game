package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {
    private Properties properties;
    private Properties defaultProperties;

    ConfigurationLoader() {
        setConfiguration();
    }

    void setConfiguration() {
        loadDefaultConfiguration();
        File config = new File("config.properties");
        if (config.exists()) {
            loadExistingConfiguration();
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

    private void loadDefaultConfiguration() {
        try {
            defaultProperties = new Properties();
            FileInputStream in = new FileInputStream("config.defaultProperties");
            defaultProperties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties = new Properties(defaultProperties);
    }

    public Properties getProperties() {
        return properties;
    }
}
