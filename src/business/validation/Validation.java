package business.validation;

import business.exception.RuleException;

import javax.swing.*;

public interface Validation {
    void validate(JPanel component) throws RuleException;
}
