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

    public boolean hasSameColor(Tableable tableable) {
        TokenColor another = ((TokenVO) tableable).getColor();
        return tokenVO.getColor() == another;
    }

    @Override
    public Tableable getTableable() {
        return tokenVO;
    }
}
