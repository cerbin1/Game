package app.view.render;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ViewObjectTest {
    @Test
    public void shouldTriggerListener() {
        // given
        ViewObject viewObject = new ViewObject(0, 0, 0, 0) {
        };
        MockRunnable listener = new MockRunnable();
        viewObject.addListener(listener);

        // when
        viewObject.triggerClicked();

        // then
        assertTrue(listener.isInvoked());
    }

    private class MockRunnable implements Runnable {
        boolean invoked;

        @Override
        public void run() {
            invoked = true;
        }

        boolean isInvoked() {
            return invoked;
        }
    }
}

