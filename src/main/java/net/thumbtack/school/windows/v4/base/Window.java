package net.thumbtack.school.windows.v4.base;

import net.thumbtack.school.windows.v4.Desktop;
import net.thumbtack.school.windows.v4.Point;
import net.thumbtack.school.windows.v4.iface.Movable;
import net.thumbtack.school.windows.v4.iface.Resizable;

public abstract class Window implements Movable, Resizable {

    private WindowState windowState;

    public Window(){
        windowState = WindowState.ACTIVE;
    }

    public Window(WindowState windowState) throws WindowException {
        setState(windowState);
    }

    public WindowState getState() {
        return windowState;
    }

    public void setState(WindowState windowState) throws WindowException {
        if (windowState == null
                || this.windowState == WindowState.DESTROYED
                || this.windowState == null && windowState == WindowState.DESTROYED) {
            throw new WindowException(WindowErrorCode.WRONG_STATE);
        } else {
            this.windowState = windowState;
        }
    }

    public abstract boolean isInside(int x, int y);

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    public abstract boolean isFullyVisibleOnDesktop(Desktop desktop);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Window window = (Window) o;

        return windowState == window.windowState;
    }

    @Override
    public int hashCode() {
        return windowState != null ? windowState.hashCode() : 0;
    }

}
