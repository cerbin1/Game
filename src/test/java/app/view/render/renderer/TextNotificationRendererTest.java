package app.view.render.renderer;

import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;

import static org.mockito.Mockito.mock;

public class TextNotificationRendererTest {
    @Test
    public void shouldRender() {
        // given
        Graphics2D graphics = mock(Graphics2D.class);
        TextNotificationRenderer renderer = new TextNotificationRenderer(20, 20);
        renderer.display("Test text");

        // when
        renderer.render(graphics);

        // then
        Mockito.verify(graphics).drawString("Test text", 20, 20);
    }
}
