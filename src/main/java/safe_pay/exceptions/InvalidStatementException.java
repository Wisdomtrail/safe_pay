package safe_pay.exceptions;

public class InvalidStatementException extends RuntimeException{

    public InvalidStatementException(String message){
        super(message);
    }
}
