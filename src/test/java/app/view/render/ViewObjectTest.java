package app.view.render;

import app.view.render.vo.ViewObject;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ViewObjectTest {
    @Test
    public void shouldTriggerListener() {
        // given
        ViewObject viewObject = new ViewObject(0, 0, 0, 0) {
        };
        MockRunnable listener = new MockRunnable();
        viewObject.addClickListener(listener);

        // when
        viewObject.triggerClicked();

        // then
        assertTrue(listener.isInvoked());
    }

    private class MockRunnable implements RendererClicked {
        boolean invoked;

        @Override
        public void clicked(ViewObject viewObject) {
            invoked = true;
        }

        boolean isInvoked() {
            return invoked;
        }
    }
}

