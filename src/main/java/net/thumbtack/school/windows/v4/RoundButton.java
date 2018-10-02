package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.RoundWindow;
import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;

public class RoundButton extends RoundWindow {

    private String text;

    public RoundButton(Point center,
                       int radius,
                       String text) {
        super(center, radius);
        setText(text);
    }

    public RoundButton(int xCenter,
                       int yCenter,
                       int radius,
                       String text) {
        this(new Point(xCenter, yCenter), radius, text);
    }

    public RoundButton(Point center,
                       int radius,
                       WindowState windowState,
                       String text) throws WindowException {
        super(center, radius, windowState);
        setText(text);
    }

    public RoundButton(Point center,
                       int radius,
                       String stateString,
                       String text) throws WindowException {
        this(center, radius, WindowState.fromString(stateString), text);
    }

    public RoundButton(int xCenter,
                       int yCenter,
                       int radius,
                       WindowState windowState,
                       String text) throws WindowException {
        this(new Point(xCenter, yCenter), radius, windowState, text);
    }

    public RoundButton(int xCenter,
                       int yCenter,
                       int radius,
                       String stateString,
                       String text) throws WindowException {
        this(new Point(xCenter, yCenter), radius, WindowState.fromString(stateString), text);
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

        RoundButton that = (RoundButton) o;

        return text != null ? text.equals(that.text) : that.text == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
