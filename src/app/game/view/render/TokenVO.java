package app.game.view.render;

import app.game.TokenColor;

public class TokenVO extends ViewObject {
    private final TokenColor color;

    public TokenVO(int x, int y, TokenColor color) {
        super(x, y, 122, 122);
        this.color = color;
    }

    public TokenColor getColor() {
        return color;
    }
}
