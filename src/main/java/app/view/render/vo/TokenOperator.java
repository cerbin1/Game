package app.view.render.vo;

import app.model.token.TokenColor;
import app.view.render.Operator;
import app.view.render.Tableable;

public class TokenOperator implements Operator {
    private TokenVO tokenVO;

    public TokenOperator(TokenVO tokenVO) {
        this.tokenVO = tokenVO;
    }

    public TokenColor getColor() {
        return tokenVO.getColor();
    }

    public boolean hasSameColor(TokenVO tokenVO) {
        return this.tokenVO.getColor() == tokenVO.getColor();
    }

    @Override
    public Tableable getTableable() {
        return tokenVO;
    }
}
