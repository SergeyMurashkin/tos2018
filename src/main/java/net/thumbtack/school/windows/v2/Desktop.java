package net.thumbtack.school.windows.v2;

class Desktop {

    private int width;
    private int height;

    public Desktop(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public Desktop() {
        this(640, 480);
    }

    public int getArea() {
        return width * height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Desktop desktop = (Desktop) o;

        if (width != desktop.width) return false;
        return height == desktop.height;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        return result;
    }
}


