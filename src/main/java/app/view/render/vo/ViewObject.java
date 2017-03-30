package app.view.render.vo;

import app.model.Updatable;
import app.view.render.AnimatedValue;
import app.view.render.RendererClicked;
import app.view.util.Resolution;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public abstract class ViewObject implements Updatable {
    private final AnimatedValue x, y, rotation;
    private final List<RendererClicked> listeners = new ArrayList<>();
    private boolean hover = false;

    final int width, height;

    public ViewObject(int x, int y, int width, int height) {
        this.x = new AnimatedValue(x);
        this.y = new AnimatedValue(y);
        this.rotation = new AnimatedValue(0);
        this.width = width;
        this.height = height;
    }

    final public Shape getOutline() {
        AffineTransform transform = new AffineTransform();
        transform.scale(0.6, 0.6);
        Resolution.scaleFullHdToResolution(transform);
        transform.translate(getX(), getY());
        transform.scale(getPerspectiveX(), getPerspectiveY());
        transform.rotate(getRotation());
        transform.translate(-getX(), -getY());
        return transform.createTransformedShape(getBaseOutline());
    }

    protected Shape getBaseOutline() {
        return new Rectangle(
                getX() - width / 2, getY() - height / 2, width, height
        );
    }

    public Point getStartingPoint() {
        return new Point((int) round(x.getStartingValue()), (int) round(y.getStartingValue()));
    }

    public Point getPosition() {
        return new Point(getX(), getY());
    }

    public int getX() {
        return (int) round(x.getValue());
    }

    public int getY() {
        return (int) round(y.getValue());
    }

    public Point getCurrentPoint() {
        return new Point((int) round(x.getDestinationValue()), (int) round(y.getDestinationValue()));
    }

    public void setRotation(double rotation) {
        this.rotation.setValue(rotation);
    }

    public double getRotation() {
        return rotation.getValue();
    }

    public void update(double secondsPassed) {
        x.update(secondsPassed);
        y.update(secondsPassed);
        rotation.update(secondsPassed);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getPerspectiveX() {
        return 1.0;
    }

    public double getPerspectiveY() {
        return 1.0;
    }

    public boolean isFlipped() {
        return getPerspectiveY() < 0 || getPerspectiveX() < 0;
    }

    public void moveTo(int x, int y, double duration) {
        this.x.setValue(x, duration);
        this.y.setValue(y, duration);
    }

    public void moveToConstantSpeed(int x, int y, double durationPer1000px) {
        double distance = distanceTo(x, y);
        this.x.setValue(x, durationPer1000px * distance / 1000.0);
        this.y.setValue(y, durationPer1000px * distance / 1000.0);
    }

    private double distanceTo(int x, int y) {
        double width = this.x.getValue() - x;
        double height = this.y.getValue() - y;
        return sqrt(pow(width, 2) + pow(height, 2));
    }

    public void moveX(int x, double duration, Runnable onFinish) {
        this.x.setValue(x, duration, onFinish);
    }

    public void moveXConstantSpeed(int x, double durationPer1000px, Runnable onFinish) {
        double distance = abs(this.getX() - x);
        this.x.setValue(x, durationPer1000px * distance / 1000.0, onFinish);
    }

    public static double slightRotation() {
        return random() * 0.03 - 0.015;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    boolean hasHover() {
        return this.hover;
    }

    public void addClickListener(RendererClicked listener) {
        listeners.add(listener);
    }

    public void triggerClicked() {
        listeners.forEach(listener -> listener.clicked(this));
    }
}
