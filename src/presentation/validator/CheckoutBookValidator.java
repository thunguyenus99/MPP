package presentation.validator;

import presentation.UIFrame;
import presentation.checkoutbook.CheckoutBookWindow;

public class CheckoutBookValidator implements Validator {
    @Override
    public void validate(UIFrame frame) throws RuleException {
        CheckoutBookWindow window = (CheckoutBookWindow) frame;
        String memberId = window.getTxtMemberId().getText().trim();
        String isbn = window.getTxtIsbn().getText().trim();
        if (memberId.isEmpty() || isbn.isEmpty()) {
            throw new RuleException("All fields must not be empty!");
        }
    }
}
