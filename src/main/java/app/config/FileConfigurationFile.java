package app.config;

import java.io.*;

public class FileConfigurationFile implements ConfigurationFile {
    private final File file;

    public FileConfigurationFile(File file) {
        this.file = file;
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
            boolean alreadyExists = file.createNewFile();
            if (!alreadyExists) {
                throw new RuntimeException("File already exists");
            }
            return new FileOutputStream(file);
        } catch (IOException e) {
            throw new ConfigurationFileException(e);
        }
    }
}
