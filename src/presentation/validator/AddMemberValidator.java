package presentation.validator;

import presentation.AddMemberWindow;
import presentation.UIFrame;

public class AddMemberValidator implements Validator {

    @Override
    public void validate(UIFrame frame) throws RuleException {
        AddMemberWindow addMemberWindow = (AddMemberWindow) frame;
        // TODO

        if (addMemberWindow.getMemberIdTxt().getText() == null || addMemberWindow.getMemberIdTxt().getText().trim().isEmpty()) {
            throw new RuleException("Member Id cannot be empty");
        }
        if(!isNumeric(addMemberWindow.getMemberIdTxt().getText()))
        {
            throw  new RuleException("Invalid data in Member Id field");
        }

        if(!isNumeric(addMemberWindow.getNumberTxt().getText()))
        {
            throw  new RuleException("Invalid data in Phone Number field");
        }

        if(addMemberWindow.getFirstNameTxt().getText() == null || addMemberWindow.getFirstNameTxt().getText().trim().isEmpty())
        {
            throw  new RuleException("First Name cannot be empty");
        }

        if(addMemberWindow.getLastNameTxt().getText() == null || addMemberWindow.getLastNameTxt().getText().trim().isEmpty())
        {
            throw  new RuleException("Last Name cannot be empty");
        }
    }

    private boolean isNumeric(String val) {
        try {
            Integer intValue = Integer.parseInt(val);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
