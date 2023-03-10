package presentation.validator;

import presentation.UIFrame;

public interface Validator {
    void validate(UIFrame frame) throws RuleException;
}
