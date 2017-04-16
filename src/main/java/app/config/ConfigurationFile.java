package app.config;

import java.io.*;

class ConfigurationFile {
    private final File file;

    ConfigurationFile(File file) {
        this.file = file;
    }

    boolean exists() {
        return file.exists();
    }

    InputStream getInputStream() {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new ConfigurationFileException(e);
        }
    }

    OutputStream getOutputStream() {
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
