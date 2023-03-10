package business.exception;

public class AddMemberException extends Exception{
    public AddMemberException() {
        super();
    }

    public AddMemberException(String msg) {
        super(msg);
    }

    public AddMemberException(Throwable t) {
        super(t);
    }
}
