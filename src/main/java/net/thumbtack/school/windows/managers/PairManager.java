package net.thumbtack.school.windows.managers;

import net.thumbtack.school.windows.v4.Desktop;
import net.thumbtack.school.windows.v4.base.Window;
import net.thumbtack.school.windows.v4.base.WindowErrorCode;
import net.thumbtack.school.windows.v4.base.WindowException;

public class PairManager<T1 extends Window, T2 extends Window> {

    private T1 firstWindow;
    private T2 secondWindow;

    public PairManager(T1 firstWindow, T2 secondWindow) throws WindowException {
        super();
        setFirstWindow(firstWindow);
        setSecondWindow(secondWindow);

    }

    public static boolean allWindowsFullyVisibleOnDesktop(PairManager pairManager1,
                                                          PairManager pairManager2,
                                                          Desktop desktop) {
        return pairManager1.getFirstWindow().isFullyVisibleOnDesktop(desktop)
                && pairManager1.getSecondWindow().isFullyVisibleOnDesktop(desktop)
                && pairManager2.getFirstWindow().isFullyVisibleOnDesktop(desktop)
                && pairManager2.getSecondWindow().isFullyVisibleOnDesktop(desktop);
    }

    public T1 getFirstWindow() {
        return firstWindow;
    }

    public void setFirstWindow(T1 firstWindow) throws WindowException {
        if (firstWindow == null) {
            throw new WindowException(WindowErrorCode.NULL_WINDOW);
        } else {
            this.firstWindow = firstWindow;
        }
    }

    public T2 getSecondWindow() {
        return secondWindow;
    }

    public void setSecondWindow(T2 secondWindow) throws WindowException {
        if (secondWindow == null) {
            throw new WindowException(WindowErrorCode.NULL_WINDOW);
        } else {
            this.secondWindow = secondWindow;
        }
    }

    public boolean allWindowsFullyVisibleOnDesktop(PairManager<T1, T2> pairManager, Desktop desktop) {
        return PairManager.allWindowsFullyVisibleOnDesktop(this, pairManager, desktop);
    }
}

