package app.view.render.vo;

import app.model.card.Figure;

public class FigureVO extends ViewObject {
    private final Figure figure;

    FigureVO(Figure figure, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.figure = figure;
    }

    public Figure getFigure() {
        return figure;
    }
}
