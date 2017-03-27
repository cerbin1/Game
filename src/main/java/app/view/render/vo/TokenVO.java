package app.view.render.vo;

import app.model.token.Token;
import app.model.token.TokenColor;
import app.view.render.Operator;
import app.view.render.Tableable;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class TokenVO extends ViewObject implements Tableable {
    private final Token token;

    public TokenVO(Token token, int x, int y) {
        super(x, y, 122, 122);
        this.token = token;
    }

    @Override
    protected Shape getBaseOutline() {
        return new Ellipse2D.Double(
                getX() - width / 2, getY() - height / 2,
                width, height);
    }

    public TokenColor getColor() {
        return token.getColor();
    }

    public boolean isVersatile() {
        return token.isVersatile();
    }

    @Override
    public Operator getOperator() {
        if (token.isVersatile()) {
            return new VersatileOperator(this);
        }
        return new TokenOperator(this);
    }

    @Override
    public void moveTo(Point previousPoint, double duration) {
        super.moveTo(previousPoint.x, previousPoint.y, duration);
    }


    @Override
    public void moveToTable(int shift) {
        super.moveTo(1800, 600 + shift, 0.5);
    }
}
