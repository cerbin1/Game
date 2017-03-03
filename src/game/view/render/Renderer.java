package game.view.render;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class Renderer {
    final ViewObject viewObject;

    Renderer(ViewObject viewObject) {
        this.viewObject = viewObject;
    }

    protected abstract void render(Graphics2D graphics);

    public void performRenderOn(Graphics2D graphics2D) {
        AffineTransform previous = graphics2D.getTransform();
        graphics2D.setTransform(getTransform());
        render(graphics2D);
        graphics2D.setTransform(previous);
    }

    private AffineTransform getTransform() {
        AffineTransform transform = new AffineTransform();
        transform.translate(viewObject.getX(), viewObject.getY());
        transform.scale(viewObject.getPerspectiveX(), viewObject.getPerspectiveY());
        transform.rotate(viewObject.getRotation());
        transform.translate(-viewObject.getWidth() / 2, -viewObject.getHeight() / 2);
        return transform;
    }
}
