package mediatheque;
public abstract class MediathequeException extends Exception {

    protected MediathequeException(String message) {
        super(message);
    }

    protected MediathequeException(String message, Throwable cause) {
        super(message, cause);
    }
}
