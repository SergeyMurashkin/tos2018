package net.thumbtack.school.windows.v3;

import net.thumbtack.school.windows.v3.base.RoundWindow;

public class RoundButton extends RoundWindow {

    private String text;

    public RoundButton(Point center,
                       int radius,
                       boolean active,
                       String text) {
        setCenter(center.getX(), center.getY());
        setRadius(radius);
        setActive(active);
        setText(text);
    }

    public RoundButton(int xCenter,
                       int yCenter,
                       int radius,
                       boolean active,
                       String text) {
        setCenter(xCenter, yCenter);
        setRadius(radius);
        setActive(active);
        setText(text);
    }

    public RoundButton(Point center,
                       int radius,
                       String text) {
        setCenter(center.getX(), center.getY());
        setRadius(radius);
        setText(text);
    }

    public RoundButton(int xCenter,
                       int yCenter,
                       int radius,
                       String text) {
        setCenter(xCenter, yCenter);
        setRadius(radius);
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
