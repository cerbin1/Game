package app.view.render.vo;

public class ButtonVO extends ViewObject {
    private final String title;

    public ButtonVO(String title, int x, int y) {
        super(x, y, 625, 126);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}