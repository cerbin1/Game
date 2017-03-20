package app.view.util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class FastClickMouseAdapter extends MouseAdapter {
    private boolean canClick;

    @Override
    public void mousePressed(MouseEvent event) {
        if (canClick) {
            mouseFastClicked(event);
            canClick = false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        canClick = true;
    }

    public abstract void mouseFastClicked(MouseEvent event);
}
