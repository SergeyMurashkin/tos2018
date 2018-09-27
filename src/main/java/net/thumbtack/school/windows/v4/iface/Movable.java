package net.thumbtack.school.windows.v4.iface;

import net.thumbtack.school.windows.v4.Point;

public interface Movable {

    void moveTo(int x, int y);

    default void moveTo(Point point) {
        moveTo(point.getX(), point.getY());
    }

    void moveRel(int dx, int dy);

}
