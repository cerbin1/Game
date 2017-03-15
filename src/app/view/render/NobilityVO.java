package app.view.render;

import app.game.card.nobility.Nobility;

public class NobilityVO extends ViewObject {
    private final Nobility nobility;

    public NobilityVO(Nobility nobility, int x, int y) {
        super(x, y, 236, 236);
        this.nobility = nobility;
    }
}
