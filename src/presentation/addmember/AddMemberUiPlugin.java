package presentation.addmember;

import presentation.UiPlugin;

public class AddMemberUiPlugin extends UiPlugin {

    public static final String NAME = "ADD_MEMBER_WINDOW";

    public AddMemberUiPlugin() {
        super(NAME, new AddMemberWindow());
    }
}
