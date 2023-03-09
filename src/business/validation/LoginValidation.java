package business.validation;

import business.exception.RuleException;
import presentation.LoginFrame;
import presentation.UIFrame;

public class LoginValidation implements Validation {

    @Override
    public void validate(UIFrame frame) throws RuleException {
        LoginFrame loginFrame = (LoginFrame) frame;
        // TODO
        String userId = loginFrame.getUserId();
        String password = loginFrame.getPassword();
    }
}
