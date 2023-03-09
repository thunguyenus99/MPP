package business.validation;

import business.exception.RuleException;
import presentation.LoginWindow;
import presentation.UIFrame;

public class LoginValidation implements Validation {

    @Override
    public void validate(UIFrame frame) throws RuleException {
        LoginWindow loginFrame = (LoginWindow) frame;
        // TODO
        String userId = loginFrame.getUserId();
        String password = loginFrame.getPassword();
    }
}
