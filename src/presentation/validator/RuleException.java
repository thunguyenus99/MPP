package presentation.validator;

final public class RuleException extends Exception {
	public RuleException() {
		super();
	}

	public RuleException(String msg) {
		super(msg);
	}

	public RuleException(Throwable t) {
		super(t);
	}
}
