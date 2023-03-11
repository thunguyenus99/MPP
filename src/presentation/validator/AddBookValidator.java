package presentation.validator;

import presentation.NumberUtils;
import presentation.UIFrame;
import presentation.addbook.AddBookWindow;

public class AddBookValidator implements Validator {
    @Override
    public void validate(UIFrame frame) throws RuleException {
        AddBookWindow addBookWindow = (AddBookWindow) frame;
        String isbn = addBookWindow.getIsbn();
        String title = addBookWindow.getTitle();
        String maxCheckoutLength = addBookWindow.getMaxCheckoutLength();
        String numOfCopies = addBookWindow.getNumOfCopies();
        String authorList = addBookWindow.getAuthorList();
        if (isbn.isEmpty() || title.isEmpty() || maxCheckoutLength.isEmpty() || numOfCopies.isEmpty() || authorList.isEmpty()) {
            throw new RuleException("No fields should be empty!");
        }
        if (!NumberUtils.isNumeric(maxCheckoutLength)) {
            throw new RuleException("Maximum checkout length should be numeric!");
        }
        try {
            if (Integer.parseInt(numOfCopies) <= 0) {
                throw new RuleException("Number of copies must be greater than 0!");
            }
        } catch (NumberFormatException ex) {
            throw new RuleException("Number of copies should be numeric!");
        }
    }
}
