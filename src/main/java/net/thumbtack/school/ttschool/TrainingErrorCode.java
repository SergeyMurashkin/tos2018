package net.thumbtack.school.ttschool;

public enum TrainingErrorCode {

    TRAINEE_WRONG_FIRSTNAME("передается null или пустая строка для Имени"),
    TRAINEE_WRONG_LASTNAME("передается null или пустая строка для Фамилии"),
    TRAINEE_WRONG_RATING("допустимый интервал для оценки от 1 до 5");

    private String errorText;

    TrainingErrorCode(String errorText) {
        this.errorText = errorText;
    }

    public String getErrorText() {
        return errorText;
    }

}
