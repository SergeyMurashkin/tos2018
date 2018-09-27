package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;

public class RadioButton extends RoundButton {

    private boolean checked;

    public RadioButton(Point center,
                       int radius,
                       WindowState windowState,
                       String text,
                       boolean checked) throws WindowException {
        super(center, radius, windowState, text);
        setChecked(checked);
    }

    public RadioButton(Point center,
                       int radius,
                       String stateString,
                       String text,
                       boolean checked) throws WindowException{
        super(center, radius, stateString, text);
        setChecked(checked);
    }

    public RadioButton(int xCenter,
                       int yCenter,
                       int radius,
                       WindowState windowState,
                       String text,
                       boolean checked) throws WindowException{
        super(xCenter, yCenter, radius, windowState, text);
        setChecked(checked);
    }

    public RadioButton(int xCenter,
                       int yCenter,
                       int radius,
                       String stateString,
                       String text,
                       boolean checked) throws WindowException{
        super(xCenter, yCenter, radius, stateString, text);
        setChecked(checked);
    }

    public RadioButton(Point center,
                       int radius,
                       String text,
                       boolean checked) {
        super(center, radius, text);
        setChecked(checked);
    }

    public RadioButton(int xCenter,
                       int yCenter,
                       int radius,
                       String text,
                       boolean checked) {
        super(xCenter, yCenter, radius, text);
        setChecked(checked);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RadioButton that = (RadioButton) o;

        return checked == that.checked;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (checked ? 1 : 0);
        return result;
    }
}
