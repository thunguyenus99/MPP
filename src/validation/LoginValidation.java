package validation;

import exception.RuleException;
import view.LoginPanel;

import javax.swing.*;
import java.awt.*;

public class LoginValidation implements Validation{

    @Override
    public void validate(JPanel component) throws RuleException {
        LoginPanel loginPanel = (LoginPanel) component;
        // TODO
        String userId = loginPanel.getUserId();
        String password = loginPanel.getPassword();
    }
}
