package business.exception;

public class AddBookException extends Exception{
    public AddBookException() {
        super();
    }

    public AddBookException(String msg) {
        super(msg);
    }

    public AddBookException(Throwable t) {
        super(t);
    }
}
