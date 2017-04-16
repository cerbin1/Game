package app.config;

class ConfigurationFileException extends RuntimeException {
    ConfigurationFileException(Exception exception) {
        super(exception);
    }
}
