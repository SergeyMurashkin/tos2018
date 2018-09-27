package net.thumbtack.school.windows.v4.base;

public enum WindowState {
    ACTIVE,
    INACTIVE,
    DESTROYED;

    public static WindowState fromString(String stateString) throws WindowException {
        if (stateString == null
                || !stateString.equals(WindowState.ACTIVE.name())
                && !stateString.equals(WindowState.INACTIVE.name())
                && !stateString.equals(WindowState.DESTROYED.name())) {
            throw new WindowException(WindowErrorCode.WRONG_STATE);
        }else{
            return WindowState.valueOf(stateString);
        }
    }

}
