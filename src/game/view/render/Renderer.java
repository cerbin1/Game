package game.view.render;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class Renderer {
    private final ViewObject viewObject;

    public Renderer(ViewObject viewObject) {
        this.viewObject = viewObject;
    }

    protected abstract void render(Graphics2D graphics);

    public void performRenderOn(Graphics2D graphics2D) {
        drawDebugOutline(graphics2D);

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

    private void drawDebugOutline(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(2));
        int x = viewObject.getX() - viewObject.getWidth() / 2;
        int y = viewObject.getY() - viewObject.getHeight() / 2;
        graphics2D.drawRect(x, y, viewObject.getWidth(), viewObject.getHeight());
    }
}
