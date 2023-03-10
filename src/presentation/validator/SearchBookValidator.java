package presentation.validator;

import presentation.UIFrame;
import presentation.searchbook.SearchBookWindow;

public class SearchBookValidator implements Validator {
    @Override
    public void validate(UIFrame frame) throws RuleException {
        SearchBookWindow window = (SearchBookWindow) frame;
        if (window.getTxtIsbn().getText().trim().isEmpty()) {
            throw new RuleException("ISBN must not be empty!");
        }
    }
}
