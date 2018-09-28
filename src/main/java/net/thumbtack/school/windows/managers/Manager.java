package net.thumbtack.school.windows.managers;

import net.thumbtack.school.windows.v4.base.Window;
import net.thumbtack.school.windows.v4.base.WindowErrorCode;
import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.iface.Movable;

public class Manager<T extends Window> implements Movable {

    private T window;

    public Manager(T window) throws WindowException {
        super();
        setWindow(window);
    }

    public T getWindow() {
        return window;
    }

    public void setWindow(T window) throws WindowException {
        if (window == null) {
            throw new WindowException(WindowErrorCode.NULL_WINDOW);
        } else {
            this.window = window;
        }
    }

    public void moveTo(int x, int y) {
        getWindow().moveTo(x, y);
    }

    @Override
    public void moveRel(int dx, int dy) {
        getWindow().moveRel(dx, dy);
    }

}
