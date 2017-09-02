package app.model.config;

import java.io.*;

class ConfigurationFile {
    private final File file;

    ConfigurationFile(File file) {
        this.file = file;
    }

    boolean exists() {
        return file.exists();
    }

    InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }

    OutputStream getOutputStream() throws IOException {
        boolean wasMissing = file.createNewFile();
        if (!wasMissing) {
            throw new RuntimeException("File existed won't be overwritten");
        }
        return new FileOutputStream(file);
    }
}
