package net.thumbtack.school.concert.requestException;

public enum RequestErrorCode {


    EMPTY_FIRSTNAME_STRING("Empty firstname string."),
    SPACE_FIRSTNAME_STRING("Space in firstname string."),
    EMPTY_LASTNAME_STRING("Empty lastname string."),
    SPACE_LASTNAME_STRING("Space in lastname string."),
    EMPTY_LOGIN_STRING("Empty login string."),
    SPACE_LOGIN_STRING("Space in login string."),
    EMPTY_PASSWORD_STRING("Empty password string."),
    SPACE_PASSWORD_STRING("Space in password string."),
    WRONG_PASSWORD_LENGTH("Short password. Use more 6 characters."),
    WRONG_PASSWORD("Wrong password."),
    LOGIN_DUPLICATE("The login already exists."),
    USER_NOT_REGISTERED("The user not registered."),
    FAILED_USER_REGISTRATION("Unsuccessful user registration."),
    FAILED_USER_LOGIN("Unsuccessful user login."),
    FAILED_USER_LOGOUT("Unsuccessful user logout."),
    FAILED_SONG_ADDING("Unsuccessful song adding."),
    FAILED_SONG_EXISTING("Song not exists."),
    FAILED_RATING_ADDING("Unsuccessful rating adding."),
    FAILED_RATING_REMOVING("Unsuccessful rating removing."),
    FAILED_COMMENT_ADDING("Unsuccessful comment adding."),
    FAILED_COMMENT_CHANGING("Unsuccessful comment changing."),
    INVALID_TOKEN("Invalid token."),
    USER_NOT_LOGGED("User not logged."),
    USER_NOT_DELETED("User not deleted."),
    SONG_DUPLICATE("The song already suggested."),
    SONG_NOT_EXISTS("The song not suggested."),
    RATING_BAN("Author rates a song by 5, automatically and decisively."),
    RATING_NOT_EXISTS("The rating not exists."),
    RATING_DUPLICATE("The rating already exists."),
    RATING_DELETION_BAN("Only rating author can affect on it."),
    COMMENT_TEXT_LENGTH("Comment text length up to 50 symbols."),
    COMMENT_NOT_EXISTS("The comment not exists."),
    COMMENT_CHANGING_BAN("Only comment author can affect on it."),
    WRONG_SONG_DURATION("Specify the song duration in seconds from 1 to 600."),
    WRONG_RATING("Specify the song rating from 1 to 5."),
    EMPTY_COMPOSER_LIST("Composers are not indicated."),
    EMPTY_AUTHOR_LIST("Authors are not indicated."),
    EMPTY_SINGER_LIST("Singer is not indicated."),
    EMPTY_SONG_TITLE("Title is not indicated.");

    private String errorString;

    RequestErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
