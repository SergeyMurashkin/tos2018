package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;

public class RectButton3D extends RectButton {

    private int zHeight;

    public RectButton3D(Point topLeft,
                        Point bottomRight,
                        WindowState windowState,
                        String text,
                        int zHeight) throws WindowException {
        super(topLeft, bottomRight, windowState, text);
        setZHeight(zHeight);
    }

    public RectButton3D(Point topLeft,
                        Point bottomRight,
                        String stateString,
                        String text,
                        int zHeight) throws WindowException{
        super(topLeft, bottomRight, stateString, text);
        setZHeight(zHeight);
    }

    public RectButton3D(int xLeft,
                        int yTop,
                        int width,
                        int height,
                        WindowState windowState,
                        String text,
                        int zHeight) throws WindowException{
        super(xLeft, yTop, width, height, windowState, text);
        setZHeight(zHeight);
    }

    public RectButton3D(int xLeft,
                        int yTop,
                        int width,
                        int height,
                        String stateString,
                        String text,
                        int zHeight) throws WindowException{
        super(xLeft, yTop, width, height, stateString, text);
        setZHeight(zHeight);
    }

    public RectButton3D(Point topLeft,
                        Point bottomRight,
                        String text,
                        int zHeight) {
        super(topLeft, bottomRight, text);
        setZHeight(zHeight);
    }

    public RectButton3D(int xLeft,
                        int yTop,
                        int width,
                        int height,
                        String text,
                        int zHeight) {
        super(xLeft, yTop, width, height, text);
        setZHeight(zHeight);
    }

    public int getZHeight() {
        return zHeight;
    }

    public void setZHeight(int zHeight) {
        this.zHeight = zHeight;
    }

    public boolean isInside(RectButton3D rectButton3D) {
        return super.isInside(rectButton3D) && rectButton3D.getZHeight() <= getZHeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RectButton3D that = (RectButton3D) o;

        return zHeight == that.zHeight;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + zHeight;
        return result;
    }
}
