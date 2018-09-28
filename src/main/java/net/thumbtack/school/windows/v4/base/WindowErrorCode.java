package net.thumbtack.school.windows.v4.base;

public enum WindowErrorCode {

    WRONG_STATE("При создании окна передается WindowState.DESTROYED или null.\n" +
            "При изменении состояния состояние устанавливается в null.\n" +
            "Окно, находящееся в WindowState.DESTROYED, переводится в иное состояние."),
    WRONG_INDEX("Передан недопустимый индекс для массива строк."),
    EMPTY_ARRAY("Массив строк равен null."),
    NULL_WINDOW("Попытка передать null вместо окна");

    private String errorString;

    WindowErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
