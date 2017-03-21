package app.presenter;

import app.model.token.TokenColor;
import app.view.render.vo.CardVO;
import app.view.render.vo.TokenVO;
import app.view.render.vo.ViewObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                if (viewObjects.stream().anyMatch(v -> ((TokenVO) v).getColor() == ((TokenVO) vo).getColor())) {
                    return false;
                }
                for (TokenColor color : TokenColor.values()) {
                    if (viewObjects.stream().filter(viewObject -> ((TokenVO) viewObject).getColor() == color).count() == 2) {
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
        if (viewObjects.contains(vo)) {
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
