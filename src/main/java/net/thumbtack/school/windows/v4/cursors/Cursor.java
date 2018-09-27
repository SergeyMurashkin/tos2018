package net.thumbtack.school.windows.v4.cursors;

import net.thumbtack.school.windows.v4.Point;
import net.thumbtack.school.windows.v4.iface.Movable;

public class Cursor implements Movable {

    private CursorForm cursorForm;
    private Point cursorPosition;

    public Cursor(int x, int y, CursorForm cursorForm) {
        cursorPosition = new Point(x, y);
        setCursorForm(cursorForm);
    }

    public Cursor(Point point, CursorForm cursorForm) {
        cursorPosition = new Point(point.getX(), point.getY());
        setCursorForm(cursorForm);
    }

    public CursorForm getCursorForm() {
        return cursorForm;
    }

    public void setCursorForm(CursorForm cursorForm) {
        this.cursorForm = cursorForm;
    }

    public int getX() {
        return cursorPosition.getX();
    }

    public void setX(int x) {
        cursorPosition.setX(x);
    }

    public int getY() {
        return cursorPosition.getY();
    }

    public void setY(int y) {
        cursorPosition.setY(y);
    }

    public void moveTo(int x, int y) {
        setX(x);
        setY(y);
    }

    public void moveRel(int dx, int dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

}
