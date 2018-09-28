package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.WindowErrorCode;
import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;

public class WindowFactory {

    static int countRectButton;
    static int countRoundButton;
    static int countWindow;

    public static RectButton createRectButton(Point leftTop,
                                              Point rightBottom,
                                              WindowState windowState,
                                              String text) throws WindowException {
        if (windowState == WindowState.DESTROYED) {
            throw new WindowException(WindowErrorCode.WRONG_STATE);
        }
        WindowFactory.countRectButton += 1;
        RectButton rectButton = new RectButton(leftTop, rightBottom, windowState, text);
        return rectButton;
    }


    public static RoundButton createRoundButton(Point center,
                                                int radius,
                                                WindowState windowState,
                                                String text) throws WindowException {
        if (windowState == WindowState.DESTROYED) {
            throw new WindowException(WindowErrorCode.WRONG_STATE);
        }
        WindowFactory.countRoundButton += 1;
        RoundButton roundButton = new RoundButton(center, radius, windowState, text);
        return roundButton;
    }

    public static int getRectButtonCount() {
        return WindowFactory.countRectButton;
    }

    public static int getRoundButtonCount() {
        return WindowFactory.countRoundButton;
    }

    public static int getWindowCount() {
        WindowFactory.countWindow = WindowFactory.countRectButton + WindowFactory.countRoundButton;
        return WindowFactory.countWindow;
    }

    public static void reset() {
        WindowFactory.countRectButton = 0;
        WindowFactory.countRoundButton = 0;
        WindowFactory.countWindow = 0;
    }

}
