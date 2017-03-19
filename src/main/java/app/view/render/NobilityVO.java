package app.view.render;

import app.model.card.nobility.Nobility;

public class NobilityVO extends FigureVO {
    private final Nobility nobility;

    public NobilityVO(Nobility nobility, int x, int y) {
        super(nobility, x, y, 236, 236);
        this.nobility = nobility;
    }

    public Nobility getNobility() {
        return nobility;
    }
}
