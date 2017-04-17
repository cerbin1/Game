package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RuntimeFileWriter extends FileWriter {
    public RuntimeFileWriter(File file) throws IOException {
        super(file);
    }

    @Override
    public void write(String string) {
        try {
            super.write(string);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
