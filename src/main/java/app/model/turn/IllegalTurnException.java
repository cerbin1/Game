package app.model.turn;

public class IllegalTurnException extends RuntimeException {
    public IllegalTurnException() {
        super();
    }

    public IllegalTurnException(String message) {
        super(message);
    }
}
