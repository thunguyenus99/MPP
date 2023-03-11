package presentation.validator;

import presentation.UIFrame;
import presentation.login.LoginWindow;

public class LoginValidator implements Validator {

    @Override
    public void validate(UIFrame frame) throws RuleException {
        LoginWindow loginFrame = (LoginWindow) frame;
        String userId = loginFrame.getUserId();
        String password = loginFrame.getPassword();
        if (userId.isEmpty() || password.isEmpty()) {
            throw new RuleException("User ID and Password must not be empty!");
        }
    }
}
