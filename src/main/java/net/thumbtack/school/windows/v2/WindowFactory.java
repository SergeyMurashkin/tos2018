package net.thumbtack.school.windows.v2;

public class WindowFactory {

    static int countRectButton;
    static int countRoundButton;
    static int countWindow;


    public static RectButton createRectButton(Point leftTop,
                                              Point rightBottom,
                                              boolean active,
                                              String text) {
        WindowFactory.countRectButton += 1;
        RectButton rectButton = new RectButton(leftTop, rightBottom, active, text);
        return rectButton;
    }

    public static RoundButton createRoundButton(Point center,
                                                int radius,
                                                boolean active,
                                                String text) {
        WindowFactory.countRoundButton += 1;
        RoundButton roundButton = new RoundButton(center, radius, active, text);
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
