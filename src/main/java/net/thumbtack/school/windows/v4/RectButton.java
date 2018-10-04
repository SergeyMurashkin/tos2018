package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.RectWindow;
import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;

public class RectButton extends RectWindow {

    private String text;

    public RectButton(Point topLeft,
                      Point bottomRight,
                      WindowState windowState,
                      String text) throws WindowException {
        super(topLeft, bottomRight, windowState);
        setText(text);
    }

    public RectButton(Point topLeft,
                      Point bottomRight,
                      String stateString,
                      String text) throws WindowException {
        this(topLeft, bottomRight, WindowState.fromString(stateString), text);
    }

    public RectButton(int xLeft,
                      int yTop,
                      int width,
                      int height,
                      WindowState windowState,
                      String text) throws WindowException {
        this(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1), windowState, text);
    }

    public RectButton(int xLeft,
                      int yTop,
                      int width,
                      int height,
                      String stateString,
                      String text) throws WindowException {
        this(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1),
                WindowState.fromString(stateString), text);

    }

    public RectButton(Point topLeft,
                      Point bottomRight,
                      String text) {
        super(topLeft, bottomRight);
        setText(text);
    }

    public RectButton(int xLeft,
                      int yTop,
                      int width,
                      int height,
                      String text) {
        this(new Point(xLeft, yTop), new Point(xLeft + width - 1, yTop + height - 1), text);
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
