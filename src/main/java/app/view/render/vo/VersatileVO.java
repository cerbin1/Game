package app.view.render.vo;

import app.model.token.Token;

public class VersatileVO extends TokenVO {
    public VersatileVO(Token token, int x, int y) {
        super(token, x, y);
        validateToken();
    }

    private void validateToken() {
        if (!isVersatile()) {
            throw new IllegalArgumentException("Cannot instantiate VersatileVO with not versatile Token.");
        }
    }
}
