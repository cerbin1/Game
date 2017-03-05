package app.view.render;

import app.game.token.Token;
import app.game.token.TokenColor;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class TokenVO extends ViewObject {
    private final Token token;

    public TokenVO(int x, int y, Token token) {
        super(x, y, 122, 122);
        this.token = token;
    }

    @Override
    public Shape getOutline() {
        return new Ellipse2D.Double(
                getX() - width / 2, getY() - height / 2,
                width, height);
    }

    TokenColor getColor() {
        return token.getColor();
    }

    public boolean isVersatile() {
        return false;
    }
}
