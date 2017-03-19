package app.view.render;

import app.model.card.Figure;

class FigureVO extends ViewObject {
    private final Figure figure;

    FigureVO(Figure figure, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.figure = figure;
    }

    Figure getFigure() {
        return figure;
    }
}
