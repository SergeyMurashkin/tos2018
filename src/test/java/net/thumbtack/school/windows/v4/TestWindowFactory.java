package net.thumbtack.school.windows.v4;

import net.thumbtack.school.windows.v4.base.WindowErrorCode;
import net.thumbtack.school.windows.v4.base.WindowException;
import net.thumbtack.school.windows.v4.base.WindowState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestWindowFactory {

    @Test
    @SuppressWarnings("unused")
    public void testWindowFactory() throws WindowException {
        RectButton rectButton1 = WindowFactory.createRectButton(new Point(10, 20), new Point(30, 40), WindowState.ACTIVE, "OK");
        RectButton rectButton2 = WindowFactory.createRectButton(new Point(110, 120), new Point(130, 140), WindowState.ACTIVE, "OK");
        RoundButton roundButton1 = WindowFactory.createRoundButton(new Point(10, 20), 10, WindowState.ACTIVE, "OK");
        RoundButton roundButton2 = WindowFactory.createRoundButton(new Point(110, 120), 10, WindowState.ACTIVE, "OK");
        RoundButton roundButton3 = WindowFactory.createRoundButton(new Point(210, 220), 10, WindowState.ACTIVE, "OK");
        assertEquals(2, WindowFactory.getRectButtonCount());
        assertEquals(3, WindowFactory.getRoundButtonCount());
        assertEquals(5, WindowFactory.getWindowCount());
        WindowFactory.reset();
        assertEquals(0, WindowFactory.getWindowCount());
    }

    @Test
    public void testWindowFactory2() {
        try {
            WindowFactory.createRoundButton(new Point(10,10),10,WindowState.DESTROYED,"OK");
        } catch (WindowException ex) {
            assertEquals(WindowErrorCode.WRONG_STATE, ex.getWindowErrorCode());
        }
        try {
            WindowFactory.createRectButton(new Point(10,10),new Point(20,20),WindowState.DESTROYED,"OK");
        } catch (WindowException ex) {
            assertEquals(WindowErrorCode.WRONG_STATE, ex.getWindowErrorCode());
        }
    }

}
