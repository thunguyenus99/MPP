package presentation.validator;

import presentation.AddCopyWindow;
import presentation.UIFrame;

public class AddBookCopyValidator implements Validator {
    @Override
    public void validate(UIFrame frame) throws RuleException {
        AddCopyWindow addCopyWindow = (AddCopyWindow) frame;
        String isbn = addCopyWindow.getIsbn();
        if (isbn.isEmpty()) {
            throw new RuleException("ISBN can not be empty.");
        }
    }
}
