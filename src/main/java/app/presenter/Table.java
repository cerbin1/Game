package app.presenter;

import app.model.token.TokenColor;
import app.view.render.CardVO;
import app.view.render.TokenVO;
import app.view.render.VersatileVO;
import app.view.render.ViewObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Table {
    private final Set<ViewObject> viewObjects = new HashSet<>();
    private final List<TokenColor> tokenColors = new ArrayList<>();

    boolean put(ViewObject vo) {
        if (viewObjects.isEmpty() && (vo instanceof CardVO || vo instanceof TokenVO)) {
            if (vo instanceof TokenVO) {
                TokenVO tokenVO = (TokenVO) vo;
                tokenColors.add(tokenVO.getColor());
            }
            viewObjects.add(vo);
            return true;
        }
        if (vo instanceof CardVO) {
            if (viewObjects.size() == 1 && viewObjects.stream().anyMatch(v -> v instanceof VersatileVO)) {
                viewObjects.add(vo);
                return true;
            } else {
                return false;
            }
        }
        if (vo instanceof VersatileVO) {
            if (viewObjects.size() == 1 && viewObjects.stream().anyMatch(v -> v instanceof CardVO)) {
                viewObjects.add(vo);
                return true;
            } else {
                return false;
            }
        }
        if (vo instanceof TokenVO) {
            if (viewObjects.size() == 1 && viewObjects.stream().anyMatch(v -> v instanceof TokenVO)) {
                viewObjects.add(vo);
                return true;
            } else {
                return false;
            }
            if (viewObjects.size() == 2 &&) {

            }
        }

        return false;
    }

    public void take(ViewObject vo) {
        if (viewObjects.contains(vo)) {
            viewObjects.remove(vo);
        } else {
            throw new IllegalViewObjectTaking("Unexpected gather");
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
