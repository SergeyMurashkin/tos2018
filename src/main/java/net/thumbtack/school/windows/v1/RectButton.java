package net.thumbtack.school.windows.v1;

public class RectButton {

    private Point topLeft;
    private Point bottomRight;
    private boolean active = true;

    public RectButton(Point topLeft,
                      Point bottomRight,
                      boolean active) {
        setTopLeft(topLeft);
        setBottomRight(bottomRight);
        this.active = active;
    }

    public RectButton(int xLeft,
                      int yTop,
                      int width,
                      int height,
                      boolean active) {
        setTopLeft(new Point(xLeft, yTop));
        setBottomRight(new Point(xLeft + width - 1, yTop + height - 1));
        this.active = active;
    }

    public RectButton(Point topLeft, Point bottomRight) {
        setTopLeft(topLeft);
        setBottomRight(bottomRight);
    }

    public RectButton(int xLeft,
                      int yTop,
                      int width,
                      int height) {
        setTopLeft(new Point(xLeft, yTop));
        setBottomRight(new Point(xLeft + width - 1, yTop + height - 1));
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
        setBottomRight(new Point(x + getWidth() - 1, y + getHeight() - 1));
        setTopLeft(new Point(x, y));
    }

    public void moveTo(Point point) {
        setBottomRight(new Point(point.getX() + getWidth() - 1, point.getY() + getHeight() - 1));
        setTopLeft(point);
    }

    public void moveRel(int dx, int dy) {
        setTopLeft(new Point(topLeft.getX() + dx, topLeft.getY() + dy));
        setBottomRight(new Point(bottomRight.getX() + dx, bottomRight.getY() + dy));
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
        setBottomRight(new Point(topLeft.getX() + newWidth - 1, topLeft.getY() + newHeight - 1));
    }

    public boolean isInside(int x, int y) {
        return topLeft.getX() <= x && x < (topLeft.getX() + getWidth())
                && topLeft.getY() <= y && y < (topLeft.getY() + getHeight());
    }

    public boolean isInside(Point point) {
        return topLeft.getX() <= point.getX() && point.getX() < (topLeft.getX() + getWidth())
                && topLeft.getY() <= point.getY() && point.getY() < (topLeft.getY() + getHeight());
    }

    public boolean isIntersects(RectButton rectButton) {
        return rectButton.getTopLeft().getX() + rectButton.getWidth() > getTopLeft().getX()
                && rectButton.getTopLeft().getX() < getTopLeft().getX() + getWidth()
                && rectButton.getTopLeft().getY() + rectButton.getHeight() > getTopLeft().getY()
                && rectButton.getTopLeft().getY() < getTopLeft().getY() + getHeight();
    }

    public boolean isInside(RectButton rectButton) {
        return rectButton.getTopLeft().getX() >= getTopLeft().getX()
                && rectButton.getTopLeft().getX() + rectButton.getWidth() <= getTopLeft().getX() + getWidth()
                && rectButton.getTopLeft().getY() >= getTopLeft().getY()
                && rectButton.getTopLeft().getY() + rectButton.getHeight() <= getTopLeft().getY() + getHeight();
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        return topLeft.isVisibleOnDesktop(desktop) && bottomRight.isVisibleOnDesktop(desktop);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RectButton that = (RectButton) o;

        if (active != that.active) return false;
        if (topLeft != null ? !topLeft.equals(that.topLeft) : that.topLeft != null) return false;
        return bottomRight != null ? bottomRight.equals(that.bottomRight) : that.bottomRight == null;
    }

    @Override
    public int hashCode() {
        int result = topLeft != null ? topLeft.hashCode() : 0;
        result = 31 * result + (bottomRight != null ? bottomRight.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }
}
