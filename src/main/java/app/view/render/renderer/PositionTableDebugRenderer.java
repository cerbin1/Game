package app.view.render.renderer;

import app.presenter.PositionTable;
import app.view.render.vo.ViewObject;
import org.newdawn.slick.Graphics;

import static org.newdawn.slick.Color.*;

public class PositionTableDebugRenderer extends Renderer {
    public PositionTableDebugRenderer(PositionTable positionTable) {
        super(new ViewObject(positionTable.getX(), positionTable.getY(), 1, 1) {
        });
    }

    @Override
    protected void render(Graphics graphics) {
        graphics.setColor(white);
        graphics.fillRect(0, 0, 330, 80);
        graphics.setColor(black);
        graphics.drawString("PositionTable", 10, 10);
        graphics.setColor(red);
        graphics.fillRect(0, 0, 10, 10);
    }
}
