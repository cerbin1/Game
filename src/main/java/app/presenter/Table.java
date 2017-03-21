package app.presenter;

import app.model.token.TokenColor;
import app.view.render.vo.CardVO;
import app.view.render.vo.TokenVO;
import app.view.render.vo.ViewObject;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static app.model.token.TokenColor.*;

public class Table {
    private final Set<ViewObject> viewObjects = new HashSet<>();
    private final Map<TokenColor, Integer> tokenColors = new EnumMap<>(TokenColor.class);

    public Table() {
        tokenColors.put(Green, 0);
        tokenColors.put(Purple, 0);
        tokenColors.put(Blue, 0);
        tokenColors.put(Black, 0);
        tokenColors.put(Red, 0);
    }

    boolean put(ViewObject vo) {
        if (viewObjects.isEmpty() && (vo instanceof CardVO || vo instanceof TokenVO)) {
            if (vo instanceof TokenVO && !((TokenVO) vo).isVersatile()) {
                putTokenColor(vo);
            }
            viewObjects.add(vo);
            return true;
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
            if (viewObjects.size() == 1 && viewObjects.stream().anyMatch(v -> (v instanceof TokenVO) && !((TokenVO) v).isVersatile())) {
                putTokenColor(vo);
                viewObjects.add(vo);
                return true;
            }
            if (viewObjects.size() == 2 && tokenColors.values().stream().noneMatch(integer -> integer == 2)) {
                putTokenColor(vo);
                viewObjects.add(vo);
                return true;
            }
            return false;
        }
        return false;
    }

    private void putTokenColor(ViewObject vo) {
        TokenVO tokenVO = (TokenVO) vo;
        tokenColors.put(tokenVO.getColor(), tokenColors.get(tokenVO.getColor()) + 1);
        System.out.println(tokenColors.get(tokenVO.getColor()));
    }

    public void take(ViewObject vo) {
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
