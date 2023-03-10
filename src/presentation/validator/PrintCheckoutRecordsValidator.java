package presentation.validator;

import presentation.UIFrame;
import presentation.printcheckoutrecords.PrintCheckOutRecordsWindow;

public class PrintCheckoutRecordsValidator implements Validator {
    @Override
    public void validate(UIFrame frame) throws RuleException {
        PrintCheckOutRecordsWindow window = (PrintCheckOutRecordsWindow) frame;
        String memberId = window.getTxtMemberId().getText().trim();
        if (memberId.isEmpty()) {
            throw new RuleException("Member ID must not be empty!");
        }
    }
}
