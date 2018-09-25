package net.thumbtack.school.windows.v3.cursors;

import net.thumbtack.school.windows.v3.Point;
import net.thumbtack.school.windows.v3.iface.Movable;

public class Cursor implements Movable {

    private int cursorForm;
    private Point cursorPosition;

    public Cursor(int x, int y, int cursorForm) {
        cursorPosition = new Point(x, y);
        setCursorForm(cursorForm);
    }

    public Cursor(Point point, int cursorForm) {
        cursorPosition = new Point(point.getX(), point.getY());
        setCursorForm(cursorForm);
    }

    public int getCursorForm() {
        return cursorForm;
    }

    public void setCursorForm(int cursorForm) {
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
