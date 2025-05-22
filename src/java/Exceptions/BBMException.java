package Exceptions;

public class BBMException extends Exception {
    public BBMException() {}

    public BBMException(String str) {
        super(str);
    }

    public BBMException(Throwable cause) {
        super(cause);
    }

    public BBMException(String str, Throwable cause) {
        super(str, cause);
    }
}
