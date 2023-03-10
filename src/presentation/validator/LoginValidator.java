package presentation.validator;

import presentation.LoginWindow;
import presentation.UIFrame;

public class LoginValidator implements Validator {

    @Override
    public void validate(UIFrame frame) throws RuleException {
        LoginWindow loginFrame = (LoginWindow) frame;
        String userId = loginFrame.getUserId();
        String password = loginFrame.getPassword();
        if (userId.isEmpty() || password.isEmpty()) {
            throw new RuleException("User ID and Password can not be empty.");
        }
        if (userId.length() != 3) {
            throw new RuleException("User ID should have 3 characters.");
        }
    }
}
