package business.validation;

import business.exception.RuleException;
import presentation.LoginPanel;

import javax.swing.*;

public class LoginValidation implements Validation {

    @Override
    public void validate(JPanel component) throws RuleException {
        LoginPanel loginPanel = (LoginPanel) component;
        // TODO
        String userId = loginPanel.getUserId();
        String password = loginPanel.getPassword();
    }
}
