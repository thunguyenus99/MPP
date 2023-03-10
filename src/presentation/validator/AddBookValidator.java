package presentation.validator;

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
            throw new RuleException("No fields should be empty.");
        }
        try {
            Integer.parseInt(maxCheckoutLength);
        } catch (NumberFormatException ex) {
            throw new RuleException("Maximum checkout length should be numeric");
        }
        try {
            Integer.parseInt(numOfCopies);
        } catch (NumberFormatException ex) {
            throw new RuleException("Number of copies should be numeric");
        }
    }
}
