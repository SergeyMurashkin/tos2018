package net.thumbtack.school.windows.v4.base;

public class WindowException extends Exception {

    private WindowErrorCode windowErrorCode;

    public WindowException(WindowErrorCode windowErrorCode) {
        this.windowErrorCode = windowErrorCode;
    }

    public WindowErrorCode getWindowErrorCode() {
        return windowErrorCode;
    }
}
