package app.view.render.vo;

import app.model.token.TokenColor;
import app.view.render.Operator;
import app.view.render.Tableable;

import java.util.List;

import static app.model.token.TokenColor.values;

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

    @Override
    public boolean put(List<Operator> operators) {
        if (operators.size() == 1) {
            if (operators.stream().anyMatch(o -> o instanceof TokenOperator)) {
                operators.add(this);
                return true;
            }
        }
        if (operators.size() == 2) {
            if (operators.stream().anyMatch(o -> o instanceof TokenOperator && ((TokenOperator) o).hasSameColor(tokenVO))) {
                return false;
            }
            for (TokenColor color : values()) {
                if (operators.stream().filter(o -> o instanceof TokenOperator && ((TokenOperator) o).getColor() == color).count() == 2) {
                    return false;
                }
            }
            operators.add(this);
            return true;
        }
        return false;
    }
}
