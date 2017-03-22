package app.presenter;

import app.model.token.TokenColor;
import app.view.render.vo.CardVO;
import app.view.render.vo.TokenVO;
import app.view.render.vo.ViewObject;

import java.util.ArrayList;
import java.util.List;

import static app.model.token.TokenColor.values;

class Table {
    private final List<ViewObject> viewObjects = new ArrayList<>();

    boolean put(ViewObject vo) {
        if (viewObjects.isEmpty()) {
            if ((vo instanceof CardVO || vo instanceof TokenVO)) {
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
                    viewObjects.add(vo);
                    return true;
                }
            }
            if (viewObjects.size() == 2) {
                if (viewObjects.stream().anyMatch(v -> v instanceof TokenVO && ((TokenVO) v).getColor() == ((TokenVO) vo).getColor())) {
                    return false;
                }
                for (TokenColor color : values()) {
                    if (viewObjects.stream().filter(v -> v instanceof TokenVO && ((TokenVO) v).getColor() == color).count() == 2) {
                        return false;
                    }
                }
                viewObjects.add(vo);
                return true;
            }
        }
        return false;
    }

    void take(ViewObject vo) {
        if (!viewObjects.contains(vo)) {
            throw new UnexpectedTakeException();
        }
        viewObjects.remove(vo);
    }

    boolean canGather() {
        if (viewObjects.isEmpty()) {
            return false;
        }
        if (viewObjects.size() == 1) {
            if (viewObjects.stream().anyMatch(v -> v instanceof CardVO)) {
                return true;
            }
            if (viewObjects.stream().anyMatch(v -> v instanceof TokenVO)) {
                return false;
            }
            if (viewObjects.stream().anyMatch(v -> v instanceof TokenVO && ((TokenVO) v).isVersatile())) {
                return false;
            }
        }
        if (viewObjects.size() == 2) {
            if (viewObjects.stream().filter(v -> v instanceof CardVO).count() + viewObjects.stream().filter(v -> v instanceof TokenVO && ((TokenVO) v).isVersatile()).count() == 2) {
                return true;
            }
            for (TokenColor color : values()) {
                if (viewObjects.stream().filter(v -> v instanceof TokenVO && ((TokenVO) v).getColor() == color).count() == 2) {
                    return true;
                }
            }
            return false;
        }
        if (viewObjects.size() == 3) {
            for (TokenColor color : values()) {
                if (viewObjects.stream().filter(v -> v instanceof TokenVO && ((TokenVO) v).getColor() == color).count() > 1) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    List<ViewObject> gather() {
        if (!canGather()) {
            throw new UnexpectedGatherException();
        }
        List<ViewObject> copy = new ArrayList<>();
        copy.addAll(viewObjects);
        viewObjects.clear();
        return copy;

    }
}
