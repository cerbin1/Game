package app.config;

import app.Application;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class ResourceConfigurationFile implements ConfigurationFile {
    private String filename;

    public ResourceConfigurationFile(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean exists() {
        return Application.class.getClassLoader().getResourceAsStream(filename) != null;
    }

    @Override
    public InputStream getInputStream() {
        return Application.class.getClassLoader().getResourceAsStream(filename);
    }

    @Override
    public OutputStream getOutputStream() {
        URL resourceUrl = getClass().getClassLoader().getResource(filename);
        try {
            File file = new File(resourceUrl.toURI());
            return new FileOutputStream(file);
        } catch (FileNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
