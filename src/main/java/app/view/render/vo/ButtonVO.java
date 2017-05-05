package app.view.render.vo;

public class ButtonVO extends ViewObject {
    private final String title;
    private boolean enabled = false;

    public ButtonVO(String title, int x, int y) {
        super(x, y, 625, 126);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean disabled) {
        this.enabled = disabled;
    }
}
