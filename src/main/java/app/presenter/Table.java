package app.presenter;

import app.model.token.TokenColor;
import app.view.render.vo.CardVO;
import app.view.render.vo.TokenVO;
import app.view.render.vo.ViewObject;

import java.util.*;

import static app.model.token.TokenColor.*;

class Table {
    private final List<ViewObject> viewObjects = new ArrayList<>();
    private final Map<TokenColor, Integer> tokenColors = new EnumMap<>(TokenColor.class);

    Table() {
        tokenColors.put(Green, 0);
        tokenColors.put(Purple, 0);
        tokenColors.put(Blue, 0);
        tokenColors.put(Black, 0);
        tokenColors.put(Red, 0);
    }

    boolean put(ViewObject vo) {
        if (viewObjects.isEmpty()) {
            if ((vo instanceof CardVO || vo instanceof TokenVO)) {
                if (vo instanceof TokenVO) {
                    if (!((TokenVO) vo).isVersatile()) {
                        putTokenColor(vo);
                    }
                }
                viewObjects.add(vo);
                return true;
            }
        }
        if (vo instanceof CardVO) {
            if (viewObjects.size() == 1) {
                if (viewObjects.stream().anyMatch(v -> ((v instanceof TokenVO) && ((TokenVO) v).isVersatile()))) {
                    viewObjects.add(vo);
                    return true;
                }
            }
            return false;
        }
        if (vo instanceof TokenVO) {
            if (((TokenVO) vo).isVersatile()) {
                if (viewObjects.size() == 1) {
                    if (viewObjects.stream().anyMatch(v -> v instanceof CardVO)) {
                        viewObjects.add(vo);
                        return true;
                    }
                    return false;
                }
            }
            if (viewObjects.size() == 1) {
                if (viewObjects.stream().anyMatch(v -> (v instanceof TokenVO) && !((TokenVO) v).isVersatile())) {
                    putTokenColor(vo);
                    viewObjects.add(vo);
                    return true;
                }
            }
            if (viewObjects.size() == 2) {
                if (viewObjects.stream().anyMatch(v -> ((TokenVO) v).getColor() == ((TokenVO) vo).getColor())) {
                    return false;
                }
                for (TokenColor color : TokenColor.values()) {
                    if (viewObjects.stream().filter(viewObject -> ((TokenVO) viewObject).getColor() == color).count() == 2) {
                        return false;
                    }
                }
                putTokenColor(vo);
                viewObjects.add(vo);
                return true;
            }
        }
        return false;
    }

    private void putTokenColor(ViewObject vo) {
        TokenVO tokenVO = (TokenVO) vo;
        TokenColor color = tokenVO.getColor();
        tokenColors.put(color, tokenColors.get(color) + 1);
    }

    void take(ViewObject vo) {
        if (viewObjects.contains(vo)) {
            if (vo instanceof TokenVO) {
                TokenVO tokenVO = (TokenVO) vo;
                tokenColors.remove(tokenVO.getColor());
            }
            viewObjects.remove(vo);
        } else {
            throw new UnexpectedTakeException("Unexpected gather");
        }
    }

    boolean canGather() {
        return true;
    }

    Set<ViewObject> gather() {
        Set<ViewObject> copy = new HashSet<>();
        copy.addAll(viewObjects);
        viewObjects.clear();
        return copy;
    }
}
