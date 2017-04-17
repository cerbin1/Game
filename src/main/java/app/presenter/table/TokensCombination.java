package app.presenter.table;

import app.model.token.TokenColor;
import app.view.render.Tableable;
import app.view.render.vo.TokenVO;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

class TokensCombination {
    static boolean validateTokenColorsCount(List<Tableable> tableables, int tokensAmount, int colorsAmount) {
        List<TokenVO> tokenVOs = tableables.stream()
                .filter(tableable -> tableable instanceof TokenVO)
                .map(tableable -> (TokenVO) tableable)
                .collect(toList());

        if (tokenVOs.size() != tokensAmount) {
            return false;
        }

        Set<TokenColor> colors = tokenVOs.stream()
                .filter(tokenVO -> !tokenVO.isVersatile())
                .map(TokenVO::getColor)
                .collect(toSet());

        return colors.size() == colorsAmount;
    }

    static boolean isTokenNotVersatile(Tableable tableable) {
        if (tableable instanceof TokenVO) {
            TokenVO tokenVO = (TokenVO) tableable;
            return !tokenVO.isVersatile();
        }
        return false;
    }
}
