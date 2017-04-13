package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class CustomizeConfiguration {
    private final Properties properties;
    private final String[] debugValues = {"true", "false"};
    private final String[] widthValues = {"1920", "1024"};
    private final String[] heightValues = {"1080", "1280"};
    private final String[] fullscreenValues = {"true", "false"};
    private String[] propertyKeys = {"debug", "width", "height", "fullscreen"};
    private final ArrayList[] possibleValues = {new ArrayList<>(Arrays.asList(debugValues)), new ArrayList<>(Arrays.asList(widthValues)),
            new ArrayList<>(Arrays.asList(heightValues)), new ArrayList<>(Arrays.asList(fullscreenValues))};

    CustomizeConfiguration(Properties properties) {
        this.properties = properties;
    }

    public void setConfiguration(String... args) {
        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < possibleValues[i].size(); j++) {
                if (possibleValues[i].contains(args[i])) {
                    properties.setProperty(propertyKeys[i], args[i]);
                }
            }
        }
    }

    public Properties getPropertiesFromArgs() {
        return properties;
    }
}
