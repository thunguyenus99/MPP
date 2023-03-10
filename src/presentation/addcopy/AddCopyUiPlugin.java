package presentation.addcopy;

import presentation.UiPlugin;

public class AddCopyUiPlugin extends UiPlugin {

    public static final String NAME = "ADD_COPY_WINDOW";

    public AddCopyUiPlugin() {
        super(NAME, new AddCopyWindow());
    }
}
