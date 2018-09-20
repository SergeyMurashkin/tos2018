package net.thumbtack.school.windows.v2;

public class RadioButton extends RoundButton {

    private boolean checked;

    public RadioButton(Point center,
                       int radius,
                       boolean active,
                       String text,
                       boolean checked) {
        super(center, radius, active, text);
        this.checked = checked;
    }

    public RadioButton(int xCenter,
                       int yCenter,
                       int radius,
                       boolean active,
                       String text,
                       boolean checked) {
        super(xCenter, yCenter, radius, active, text);
        this.checked = checked;
    }

    public RadioButton(Point center,
                       int radius,
                       String text,
                       boolean checked) {
        super(center, radius, text);
        this.checked = checked;
    }

    public RadioButton(int xCenter,
                       int yCenter,
                       int radius,
                       String text,
                       boolean checked) {
        super(xCenter, yCenter, radius, text);
        this.checked = checked;
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
