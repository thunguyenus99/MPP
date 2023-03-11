package presentation.validator;

import presentation.NumberUtils;
import presentation.UIFrame;
import presentation.addmember.AddMemberWindow;

public class AddMemberValidator implements Validator {

    @Override
    public void validate(UIFrame frame) throws RuleException {
        AddMemberWindow window = (AddMemberWindow) frame;
        String memberId = window.getMemberIdTxt().getText().trim();
        if (memberId.isEmpty()) {
            throw new RuleException("Member ID must not be empty!");
        }
        if (!NumberUtils.isNumeric(memberId)) {
            throw new RuleException("Member ID invalid!");
        }

        if (!NumberUtils.isNumeric(window.getNumberTxt().getText().trim())) {
            throw new RuleException("Phone Number invalid!");
        }

        if (window.getFirstNameTxt().getText().trim().isEmpty()) {
            throw new RuleException("First Name must not be empty!");
        }

        if (window.getLastNameTxt().getText().trim().isEmpty()) {
            throw new RuleException("Last Name must not be empty!");
        }
    }
}
