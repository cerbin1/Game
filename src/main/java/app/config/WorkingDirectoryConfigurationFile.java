package app.config;

import java.io.*;

public class WorkingDirectoryConfigurationFile implements ConfigurationFile {
    private final File file;

    public WorkingDirectoryConfigurationFile(String filename) {
        this.file = new File(filename);
    }

    @Override
    public boolean exists() {
        return file.exists();
    }

    @Override
    public InputStream getInputStream() {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new ConfigurationFileException(e);
        }
    }

    @Override
    public OutputStream getOutputStream() {
        try {
            return new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new ConfigurationFileException(e);
        }
    }
}
