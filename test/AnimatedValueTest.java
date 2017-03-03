import app.game.view.render.AnimatedValue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnimatedValueTest {
    @Test
    public void shouldGetValue() {
        // given
        AnimatedValue animatedValue = new AnimatedValue(14);

        // when
        double value = animatedValue.getValue();

        // then
        assertEquals(value, 14, 0.000001);
    }

    @Test
    public void shouldUpdateToCurrent() {
        // given
        AnimatedValue animatedValue = new AnimatedValue(14);

        // when
        animatedValue.update(0.1);

        // then
        assertEquals(animatedValue.getValue(), 14, 0.000001);
    }

    @Test
    public void shouldUpdateUpTo() {
        // given
        AnimatedValue animatedValue = new AnimatedValue(10);
        animatedValue.setValue(11);

        // when
        animatedValue.update(0.1);

        // then
        assertEquals(animatedValue.getValue(), 10.1, 0.000001);
    }

    @Test
    public void shouldUpdateDownTo() {
        // given
        AnimatedValue animatedValue = new AnimatedValue(10);
        animatedValue.setValue(9);

        // when
        animatedValue.update(0.1);

        // then
        assertEquals(animatedValue.getValue(), 9.9, 0.000001);
    }

    @Test
    public void shouldUpdateUpToLimit() {
        // given
        AnimatedValue animatedValue = new AnimatedValue(10);
        animatedValue.setValue(11);

        // when
        animatedValue.update(1.5);

        // then
        assertEquals(animatedValue.getValue(), 11.0, 0.000001);
    }

    @Test
    public void shouldUpdateDownToLimit() {
        // given
        AnimatedValue animatedValue = new AnimatedValue(10);
        animatedValue.setValue(9);

        // when
        animatedValue.update(1.5);

        // then
        assertEquals(animatedValue.getValue(), 9.0, 0.000001);
    }
}