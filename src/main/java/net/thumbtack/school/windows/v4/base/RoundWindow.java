package net.thumbtack.school.windows.v4.base;

import net.thumbtack.school.windows.v4.Desktop;
import net.thumbtack.school.windows.v4.Point;

public abstract class RoundWindow extends Window {

    private Point center;
    private int radius;

    public RoundWindow(Point center, int radius) {
        super();
        setCenter(center);
        setRadius(radius);
    }

    public RoundWindow(Point center, int radius, WindowState windowState) throws WindowException {
        super(windowState);
        setCenter(center);
        setRadius(radius);
    }

    public Point getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setCenter(int x, int y) {
        setCenter(new Point(x,y));
    }

    public void setCenter(Point center){
        this.center = center;
    }

    public void moveTo(int x, int y) {
        center.moveTo(x, y);
    }

    public void moveRel(int dx, int dy) {
        center.moveRel(dx, dy);
    }

    public void resize(double ratio) {
        setRadius((int) ((double) getRadius() * ratio));
        if (getRadius() < 1) {
            setRadius(1);
        }
    }

    public boolean isInside(int x, int y) {
        double dx = (double) getCenter().getX() - x;
        double dy = (double) getCenter().getY() - y;
        int l = (int) Math.sqrt(dx * dx + dy * dy);
        return getRadius() >= l;
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        return getRadius() <= getCenter().getX()
                && getCenter().getX() + getRadius() < desktop.getWidth()
                && getRadius() <= getCenter().getY()
                && getCenter().getY() + getRadius() < desktop.getHeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RoundWindow that = (RoundWindow) o;

        if (radius != that.radius) return false;
        return center != null ? center.equals(that.center) : that.center == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (center != null ? center.hashCode() : 0);
        result = 31 * result + radius;
        return result;
    }
}
