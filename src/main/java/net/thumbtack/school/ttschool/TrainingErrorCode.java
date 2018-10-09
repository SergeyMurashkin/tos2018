package net.thumbtack.school.ttschool;

public enum TrainingErrorCode {

    TRAINEE_WRONG_FIRSTNAME("передается null или пустая строка для Имени учащегося"),
    TRAINEE_WRONG_LASTNAME("передается null или пустая строка для Фамилии учащегося"),
    TRAINEE_WRONG_RATING("допустимый интервал для оценки от 1 до 5"),
    GROUP_WRONG_NAME("передается null или пустая строка для названия группы"),
    GROUP_WRONG_ROOM("передается null или пустая строка для номера комнаты или происходит попытка изменить аудиторию"),
    TRAINEE_NOT_FOUND("такого учащегося не существует"),
    SCHOOL_WRONG_NAME("передается null или пустая строка для названия школы"),
    DUPLICATE_GROUP_NAME("группа с таким именем уже существует"),
    GROUP_NOT_FOUND("группа не найдена"),
    DUPLICATE_TRAINEE("такой учащийся уже существует"),
    EMPTY_TRAINEE_QUEUE("очередь уже пустая");

    private String errorText;

    TrainingErrorCode(String errorText) {
        this.errorText = errorText;
    }

    public String getErrorText() {
        return errorText;
    }

}
