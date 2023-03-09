package exception;

public class AddBookCopyException extends Exception{
    public AddBookCopyException() {
        super();
    }

    public AddBookCopyException(String msg) {
        super(msg);
    }

    public AddBookCopyException(Throwable t) {
        super(t);
    }
}
