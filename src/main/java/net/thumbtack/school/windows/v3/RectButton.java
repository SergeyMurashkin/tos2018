package net.thumbtack.school.windows.v3;

import net.thumbtack.school.windows.v3.base.RectWindow;

public class RectButton extends RectWindow {

    private String text;

    public RectButton(Point topLeft,
                      Point bottomRight,
                      boolean active,
                      String text) {
        setTopLeft(topLeft);
        setBottomRight(bottomRight);
        setActive(active);
        setText(text);
    }

    public RectButton(int xLeft,
                      int yTop,
                      int width,
                      int height,
                      boolean active,
                      String text) {
        setTopLeft(new Point(xLeft, yTop));
        setBottomRight(new Point(xLeft + width - 1, yTop + height - 1));
        setActive(active);
        setText(text);
    }

    public RectButton(Point topLeft,
                      Point bottomRight,
                      String text) {
        setTopLeft(topLeft);
        setBottomRight(bottomRight);
        setText(text);
    }

    public RectButton(int xLeft,
                      int yTop,
                      int width,
                      int height,
                      String text) {
        setTopLeft(new Point(xLeft, yTop));
        setBottomRight(new Point(xLeft + width - 1, yTop + height - 1));
        setText(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RectButton that = (RectButton) o;

        return text != null ? text.equals(that.text) : that.text == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
