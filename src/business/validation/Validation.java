package business.validation;

import business.exception.RuleException;
import presentation.UIFrame;

import javax.swing.*;

public interface Validation {
    void validate(UIFrame frame) throws RuleException;
}
