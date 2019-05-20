package be.vdab.luigi.exception;

public class KoersClientException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public KoersClientException(String message) {
        super(message);
    }
}
