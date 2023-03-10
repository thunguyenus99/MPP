package business.validation;

import business.exception.RuleException;
import presentation.SearchBookWindow;
import presentation.UIFrame;

public class SearchBookValidation implements Validation {
    @Override
    public void validate(UIFrame frame) throws RuleException {
        SearchBookWindow window = (SearchBookWindow) frame;
        if (window.getTxtIsbn().getText().trim().isEmpty()) {
            throw new RuleException("ISBN must not be empty!");
        }
    }
}
