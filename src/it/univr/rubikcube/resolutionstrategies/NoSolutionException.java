package it.univr.rubikcube.resolutionstrategies;

/**
 * Exception thrown when the selected resolution strategy does not find a
 * solution.
 * @author Alessandro Menti
 */
public class NoSolutionException extends Exception {
    /**
     * UID used for serialization.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Creates a new NoSolutionException.
     */
    public NoSolutionException() {
        super();
    }
    /**
     * Creates a new NoSolutionException given a message.
     * @param message Exception message.
     */
    public NoSolutionException(final String message) {
        super(message);
    }
    /**
     * Creates a new NoSolutionException given a cause.
     * @param cause The exception cause.
     */
    public NoSolutionException(final Throwable cause) {
        super(cause);
    }
    /**
     * Creates a new NoSolutionException given a message and a cause.
     * @param message Exception message.
     * @param cause The exception cause.
     */
    public NoSolutionException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
