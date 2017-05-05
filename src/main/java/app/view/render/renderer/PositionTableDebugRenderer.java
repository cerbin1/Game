package app.view.render.renderer;

import app.presenter.PositionTable;
import app.view.render.vo.ViewObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class PositionTableDebugRenderer extends Renderer {
    public PositionTableDebugRenderer(PositionTable positionTable) {
        super(new ViewObject(positionTable.getX(), positionTable.getY(), 1, 1) {
        });
    }

    @Override
    protected void render(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, 330, 80);
        graphics.setColor(Color.black);
        graphics.drawString("PositionTable", 10, 10);
        graphics.setColor(Color.red);
        graphics.fillRect(0, 0, 10, 10);
    }
}
