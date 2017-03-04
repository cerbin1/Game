package app.game.view.render;

import app.game.token.Token;
import app.game.token.TokenColor;

public class TokenVO extends ViewObject {
    private final Token token;

    public TokenVO(int x, int y, Token token) {
        super(x, y, 122, 122);
        this.token = token;
    }

    TokenColor getColor() {
        return token.getColor();
    }
}
