package app.model.token;

public class Token {
    private final TokenColor color;

    public Token(TokenColor color) {
        this.color = color;
    }

    public TokenColor getColor() {
        return color;
    }

    public boolean isVersatile() {
        return color == null;
    }
}
