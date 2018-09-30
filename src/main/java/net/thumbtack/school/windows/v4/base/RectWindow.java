package net.thumbtack.school.windows.v4.base;

import net.thumbtack.school.windows.v4.Desktop;
import net.thumbtack.school.windows.v4.Point;

public abstract class RectWindow extends Window {

    private Point topLeft;
    private Point bottomRight;

    public RectWindow(Point topLeft,
                      Point bottomRight,
                      WindowState windowState) throws WindowException {
        super(windowState);
        setTopLeft(topLeft);
        setBottomRight(bottomRight);
    }


    public RectWindow(Point topLeft,
                      Point bottomRight) {
        super();
        setTopLeft(topLeft);
        setBottomRight(bottomRight);
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }

    public int getWidth() {
        return bottomRight.getX() - topLeft.getX() + 1;
    }

    public int getHeight() {
        return bottomRight.getY() - topLeft.getY() + 1;
    }

    public void moveTo(int x, int y) {
        bottomRight.moveTo(x + getWidth() - 1, y + getHeight() - 1);
        topLeft.moveTo(x, y);
    }

    public void moveRel(int dx, int dy) {
        topLeft.moveRel(dx, dy);
        bottomRight.moveRel(dx, dy);
    }

    public void resize(double ratio) {
        int newWidth = (int) ((double) getWidth() * ratio);
        if (newWidth == 0) {
            newWidth = 1;
        }
        int newHeight = (int) ((double) getHeight() * ratio);
        if (newHeight == 0) {
            newHeight = 1;
        }
        bottomRight.moveTo(getTopLeft().getX() + newWidth - 1, getTopLeft().getY() + newHeight - 1);
    }

    public boolean isInside(int x, int y) {
        return getTopLeft().getX() <= x
                && x < getTopLeft().getX() + getWidth()
                && getTopLeft().getY() <= y
                && y < getTopLeft().getY() + getHeight();
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        return getTopLeft().isVisibleOnDesktop(desktop)
                && getBottomRight().isVisibleOnDesktop(desktop);
    }

    public boolean isIntersects(RectWindow rectWindow) {
        return rectWindow.getTopLeft().getX() + rectWindow.getWidth() > getTopLeft().getX()
                && rectWindow.getTopLeft().getX() < getTopLeft().getX() + getWidth()
                && rectWindow.getTopLeft().getY() + rectWindow.getHeight() > getTopLeft().getY()
                && rectWindow.getTopLeft().getY() < getTopLeft().getY() + getHeight();
    }

    public boolean isInside(RectWindow rectWindow) {
        return rectWindow.getTopLeft().getX() >= getTopLeft().getX()
                && rectWindow.getTopLeft().getX() + rectWindow.getWidth() <= getTopLeft().getX() + getWidth()
                && rectWindow.getTopLeft().getY() >= getTopLeft().getY()
                && rectWindow.getTopLeft().getY() + rectWindow.getHeight() <= getTopLeft().getY() + getHeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RectWindow that = (RectWindow) o;

        if (topLeft != null ? !topLeft.equals(that.topLeft) : that.topLeft != null) return false;
        return bottomRight != null ? bottomRight.equals(that.bottomRight) : that.bottomRight == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (topLeft != null ? topLeft.hashCode() : 0);
        result = 31 * result + (bottomRight != null ? bottomRight.hashCode() : 0);
        return result;
    }
}
