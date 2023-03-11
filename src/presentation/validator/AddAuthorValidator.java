package presentation.validator;

import presentation.UIFrame;
import presentation.addauthor.AddAuthorWindow;

public class AddAuthorValidator implements Validator {
    @Override
    public void validate(UIFrame frame) throws RuleException {
        AddAuthorWindow addAuthorWindow = (AddAuthorWindow) frame;
        String firstName = addAuthorWindow.getFirstName();
        String lastName = addAuthorWindow.getLastName();
        if (firstName.isEmpty() || lastName.isEmpty()) {
            throw new RuleException("Name must not be empty!");
        }
    }
}
