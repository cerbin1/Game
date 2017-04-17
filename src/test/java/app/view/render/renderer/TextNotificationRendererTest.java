package app.view.render.renderer;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.verification.VerificationMode;
import org.newdawn.slick.Graphics;

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

        Graphics graphics = mock(Graphics.class);

        // when
        renderer.render(graphics);

        // then
        verify(graphics, once()).drawString("Test text", 20, 20);
    }

    @Test
    public void shouldRenderThreeStrings() {
        // given
        TextNotificationRenderer renderer = new TextNotificationRenderer(20, 20);
        renderer.display("First text");
        renderer.display("Second text");
        renderer.display("Third text");

        Graphics graphics = mock(Graphics.class);

        // when
        renderer.render(graphics);

        // then
        ArgumentCaptor<String> texts = forClass(String.class);
        verify(graphics, times(3))
                .drawString(texts.capture(), eq(20.0f), any(Integer.class));

        assertEquals(asList("First text", "Second text", "Third text"), texts.getAllValues());
    }

    @Test
    public void shouldRenderTwoStringsOneBelowTheSecond() {
        // given
        TextNotificationRenderer renderer = new TextNotificationRenderer(20, 20);
        renderer.display("First text");
        renderer.display("Second text");
        renderer.display("Third text");

        Graphics graphics = mock(Graphics.class);

        // when
        renderer.render(graphics);

        // then
        ArgumentCaptor<Float> yValues = forClass(Float.class);
        verify(graphics, times(3))
                .drawString(any(String.class), eq(20.0f), yValues.capture());

        assertEquals(asList(20.0f, 50.0f, 80.0f), yValues.getAllValues());
    }

    @Test
    public void shouldNotRenderAfterTimeRunsOut() {
        // given
        TextNotificationRenderer renderer = new TextNotificationRenderer(20, 20);
        renderer.display("First text", 3.0);
        renderer.display("Second text", 6.0);

        renderer.update(3.5);

        Graphics graphics = mock(Graphics.class);

        // when
        renderer.render(graphics);

        // then
        verify(graphics, once()).drawString("Second text", 20, 20);
    }

    @Test
    public void shouldRenderAfterMultipleUpdated() {
        // given
        TextNotificationRenderer renderer = new TextNotificationRenderer(20, 20);
        renderer.update(3.5);
        renderer.display("First text", 1.0);
        renderer.update(0.5);

        Graphics graphics = mock(Graphics.class);

        // when
        renderer.render(graphics);

        // then
        verify(graphics, once()).drawString("First text", 20, 20);
    }

    private static VerificationMode once() {
        return times(1);
    }
}
