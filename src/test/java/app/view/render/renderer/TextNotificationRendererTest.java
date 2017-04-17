package app.view.render.renderer;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.awt.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.*;

public class TextNotificationRendererTest {

    @Test
    public void shouldRenderText() {
        // given
        TextNotificationRenderer renderer = new TextNotificationRenderer(20, 20);
        renderer.display("Test text");

        Graphics2D graphics = mock(Graphics2D.class);

        // when
        renderer.render(graphics);

        // then
        verify(graphics).drawString("Test text", 20, 20);
    }

    @Test
    public void shouldRenderTwoStrings() {
        // given
        TextNotificationRenderer renderer = new TextNotificationRenderer(20, 20);
        renderer.display("First text");
        renderer.display("Second text");
        renderer.display("Third text");

        Graphics2D graphics = mock(Graphics2D.class);

        // when
        renderer.render(graphics);

        // then
        ArgumentCaptor<String> texts = forClass(String.class);
        verify(graphics, times(2))
                .drawString(texts.capture(), any(Integer.class), any(Integer.class));

        assertEquals(asList("First text", "Second text", "Third text"), texts.getAllValues());
    }

    @Test
    public void shouldRenderTwoStringsOneBelowTheSecond() {
        // given
        TextNotificationRenderer renderer = new TextNotificationRenderer(20, 20);
        renderer.display("First text");
        renderer.display("Second text");
        renderer.display("Third text");

        Graphics2D graphics = mock(Graphics2D.class);

        // when
        renderer.render(graphics);

        // then
        ArgumentCaptor<Integer> yValues = forClass(Integer.class);
        verify(graphics, times(2))
                .drawString(any(String.class), any(Integer.class), yValues.capture());

        assertEquals(asList(20, 50, 80), yValues.getAllValues());
    }
}
