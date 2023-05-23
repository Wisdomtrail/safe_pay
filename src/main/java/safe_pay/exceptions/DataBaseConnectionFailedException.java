package safe_pay.exceptions;

public class DataBaseConnectionFailedException extends RuntimeException {
    public DataBaseConnectionFailedException(String message) {
        super(message);
    }
}
