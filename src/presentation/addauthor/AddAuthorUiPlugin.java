package presentation.addauthor;

import presentation.UiPlugin;
import presentation.addbook.AddBookWindow;

public class AddAuthorUiPlugin extends UiPlugin {
    public static final String NAME = "ADD_AUTHOR_WINDOW";

    public AddAuthorUiPlugin(AddBookWindow addBookWindow) {
        super(NAME, new AddAuthorWindow(addBookWindow));
    }
}
