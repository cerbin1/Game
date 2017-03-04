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
        assertEquals(14, value, 0.000001);
    }

    @Test
    public void shouldUpdateToCurrent() {
        // given
        AnimatedValue animatedValue = new AnimatedValue(14);

        // when
        animatedValue.update(1.1);

        // then
        assertEquals(14, animatedValue.getValue(), 0.000001);
    }

    @Test
    public void shouldUpdateUpTo() {
        // given
        AnimatedValue animatedValue = new AnimatedValue(10);
        animatedValue.setValue(11);

        // when
        animatedValue.update(0.1);

        // then
        assertEquals(10.1, animatedValue.getValue(), 0.000001);
    }

    @Test
    public void shouldUpdateDownTo() {
        // given
        AnimatedValue animatedValue = new AnimatedValue(10);
        animatedValue.setValue(9);

        // when
        animatedValue.update(0.1);

        // then
        assertEquals(9.9, animatedValue.getValue(), 0.000001);
    }

    @Test
    public void shouldUpdateUpToLimit() {
        // given
        AnimatedValue animatedValue = new AnimatedValue(10);
        animatedValue.setValue(11);

        // when
        animatedValue.update(1.5);

        // then
        assertEquals(11.0, animatedValue.getValue(), 0.000001);
    }

    @Test
    public void shouldUpdateDownToLimit() {
        // given
        AnimatedValue animatedValue = new AnimatedValue(10);
        animatedValue.setValue(9);

        // when
        animatedValue.update(1.5);

        // then
        assertEquals(9.0, animatedValue.getValue(), 0.000001);
    }

    @Test
    public void shouldUpdateUpToWithExceedingSpeed() {
        // given
        AnimatedValue animatedValue = new AnimatedValue(10);
        animatedValue.setValue(15, 2.5);

        // when
        animatedValue.update(5.0);

        // then
        assertEquals(animatedValue.getValue(), 15.0, 0.000001);
    }

    @Test
    public void shouldUpdateUpToWithDifferentSpeed() {
        // given
        AnimatedValue animatedValue = new AnimatedValue(10);
        animatedValue.setValue(25, 5.0);

        // when
        animatedValue.update(2.5);

        // then
        assertEquals(17.5, animatedValue.getValue(), 0.000001);

    }
}
