package app.model.config;

class ConfigurationFileException extends RuntimeException {
    ConfigurationFileException(Exception exception) {
        super(exception);
    }
}
