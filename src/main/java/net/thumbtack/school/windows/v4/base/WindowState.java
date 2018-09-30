package net.thumbtack.school.windows.v4.base;

public enum WindowState {
    ACTIVE,
    INACTIVE,
    DESTROYED;

    public static WindowState fromString(String stateString) throws WindowException {
        try {
            return WindowState.valueOf(stateString);
        } catch (NullPointerException | IllegalArgumentException ex) {
            throw new WindowException(WindowErrorCode.WRONG_STATE);
        }
    }

}
