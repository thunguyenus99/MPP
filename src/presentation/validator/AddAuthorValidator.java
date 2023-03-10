package presentation.validator;

import data.model.Address;
import presentation.UIFrame;
import presentation.addauthor.AddAuthorWindow;

public class AddAuthorValidator implements Validator {
    @Override
    public void validate(UIFrame frame) throws RuleException {
        AddAuthorWindow addAuthorWindow = (AddAuthorWindow) frame;
        String firstName = addAuthorWindow.getFirstName();
        String lastName = addAuthorWindow.getLastName();
        Address address = addAuthorWindow.getAddress();
        String phone = addAuthorWindow.getPhone();
        String confidentials = addAuthorWindow.getConfidentials();
        String biography = addAuthorWindow.getBiography();
        if (firstName.isEmpty() || lastName.isEmpty()) {
            throw new RuleException("Name can not be empty.");
        }
    }
}
