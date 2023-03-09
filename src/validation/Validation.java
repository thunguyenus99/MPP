package validation;

import exception.RuleException;

import javax.swing.*;
import java.awt.*;

public interface Validation {
    void validate(JPanel component) throws RuleException;
}
