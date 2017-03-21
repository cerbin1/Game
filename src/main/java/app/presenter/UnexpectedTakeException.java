package app.presenter;

class UnexpectedTakeException extends RuntimeException{

    UnexpectedTakeException(String message) {
        super(message);
    }
}
