package app.view.render.renderer;

import app.view.render.vo.ViewObject;
import org.newdawn.slick.Graphics;

import java.awt.*;

import static java.awt.Toolkit.getDefaultToolkit;

public abstract class Renderer {
    private final static Dimension screenSize = getDefaultToolkit().getScreenSize();

    protected final ViewObject viewObject;

    protected Renderer(ViewObject viewObject) {
        this.viewObject = viewObject;
    }

    protected abstract void render(Graphics graphics);

    public void renderOn(Graphics graphics2D) {
        graphics2D.pushTransform();
        performTransform(graphics2D);
        performRender(graphics2D);
        graphics2D.popTransform();
    }

    private void performRender(Graphics graphics2D) {
        render(graphics2D);
    }

    private void performTransform(Graphics graphics2D) {
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        graphics2D.scale(0.6f, 0.6f);
        graphics2D.scale((float) width / 1920.0f, (float) height / 1080.0f);

        graphics2D.translate(viewObject.getX(), viewObject.getY());
        graphics2D.scale((float) viewObject.getPerspectiveX(), (float) viewObject.getPerspectiveY());
        graphics2D.rotate(0, 0, (float) viewObject.getRotation());
        graphics2D.translate(-viewObject.getWidth() / 2, -viewObject.getHeight() / 2);

    }

    private void drawOutline(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setColor(Color.red);
        graphics2D.draw(viewObject.getOutline());
    }

    public ViewObject getViewObject() {
        return viewObject;
    }

    public boolean isHoverable() {
        return true;
    }
}
