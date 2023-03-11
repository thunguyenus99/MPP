package presentation.validator;

import presentation.UIFrame;
import presentation.addcopy.AddCopyWindow;

public class AddBookCopyValidator implements Validator {
    @Override
    public void validate(UIFrame frame) throws RuleException {
        AddCopyWindow addCopyWindow = (AddCopyWindow) frame;
        String isbn = addCopyWindow.getIsbn();
        if (isbn.isEmpty()) {
            throw new RuleException("ISBN must not be empty!");
        }
    }
}
