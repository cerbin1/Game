package app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static java.util.Arrays.asList;

public class CustomizeConfiguration {
    private final Properties properties;

    private final String[] debugValues = {"true", "false"};
    private final String[] widthValues = {"1920", "1024"};
    private final String[] heightValues = {"1080", "1280"};
    private final String[] fullscreenValues = {"true", "false"};
    private final String[] propertyKeys = {"debug", "width", "height", "fullscreen"};
    private final List[] possibleValues = {asList(debugValues), asList(widthValues),
            asList(heightValues), asList(fullscreenValues)};

    CustomizeConfiguration(Properties properties, String... args) {
        this.properties = properties;
        setConfigurationFromArgs(args);
    }

    private void setConfigurationFromArgs(String... args) {
        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < possibleValues[i].size(); j++) {
                if (possibleValues[i].contains(args[i])) {
                    properties.setProperty(propertyKeys[i], args[i]);
                }
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("config.properties");
            properties.store(fileOutputStream, null);
            fileOutputStream.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
