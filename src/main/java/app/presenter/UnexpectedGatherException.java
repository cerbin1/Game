package app.presenter;

class UnexpectedGatherException extends RuntimeException {
    UnexpectedGatherException(String message) {
        super(message);
    }
}
