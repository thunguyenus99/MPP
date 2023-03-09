package exception;

public class GetCheckoutRecordException extends Exception{
    public GetCheckoutRecordException() {
        super();
    }

    public GetCheckoutRecordException(String msg) {
        super(msg);
    }

    public GetCheckoutRecordException(Throwable t) {
        super(t);
    }
}
