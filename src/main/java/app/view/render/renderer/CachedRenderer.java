package app.view.render.renderer;

import app.view.render.vo.ViewObject;

import java.awt.*;
import java.awt.image.BufferedImage;

import static app.view.util.Optimization.createCompatibleImage;

public abstract class CachedRenderer extends Renderer {
    private final BufferedImage cache;
    private final Graphics2D cacheGraphics;
    private boolean shouldRender = true;

    public CachedRenderer(ViewObject viewObject) {
        super(viewObject);
        cache = createCompatibleImage(viewObject.getWidth(), viewObject.getHeight());
        cacheGraphics = cache.createGraphics();
    }

    protected void invalidate() {
        shouldRender = true;
    }

    @Override
    protected void performRender(Graphics2D graphics2D) {
        if (shouldRender) {
            render(cacheGraphics);
            shouldRender = false;
        }
        graphics2D.drawImage(cache, 0, 0, null);
    }
}
