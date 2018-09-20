package net.thumbtack.school.windows.v2;

public class RectButton3D extends RectButton {

    private int zHeight;

    public RectButton3D(Point topLeft,
                        Point bottomRight,
                        boolean active,
                        String text,
                        int zHeight) {
        super(topLeft, bottomRight, active, text);
        this.zHeight = zHeight;
    }

    public RectButton3D(int xLeft,
                        int yTop,
                        int width,
                        int height,
                        boolean active,
                        String text,
                        int zHeight) {
        super(xLeft, yTop, width, height, active, text);
        this.zHeight = zHeight;
    }

    public RectButton3D(Point topLeft,
                        Point bottomRight,
                        String text,
                        int zHeight) {
        super(topLeft, bottomRight, text);
        this.zHeight = zHeight;
    }

    public RectButton3D(int xLeft,
                        int yTop,
                        int width,
                        int height,
                        String text,
                        int zHeight) {
        super(xLeft, yTop, width, height, text);
        this.zHeight = zHeight;
    }


    public int getZHeight() {
        return zHeight;
    }

    public void setZHeight(int zHeight) {
        this.zHeight = zHeight;
    }

    public boolean isInside(RectButton3D rectButton3D) {
        return super.isInside(rectButton3D) && rectButton3D.getZHeight() <= zHeight;
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
