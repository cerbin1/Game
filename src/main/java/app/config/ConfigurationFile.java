package app.config;

import java.io.InputStream;
import java.io.OutputStream;

public interface ConfigurationFile {
    boolean exists();

    InputStream getInputStream();

    OutputStream getOutputStream();
}
