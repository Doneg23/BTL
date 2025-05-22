package Exceptions;

public class LevelLoadingException extends BBMException {
    public LevelLoadingException() {}

    public LevelLoadingException(String str) {
        super(str);
    }

    public LevelLoadingException(String str, Throwable cause) {
        super(str, cause);
    }

    public LevelLoadingException(Throwable cause) {
        super(cause);
    }
}
