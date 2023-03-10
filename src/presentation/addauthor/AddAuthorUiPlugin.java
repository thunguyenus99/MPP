package presentation.addauthor;

import presentation.UiPlugin;
import presentation.addbook.AddBookWindow;

public class AddAuthorUiPlugin extends UiPlugin {
    public static final String ADD_AUTHOR_WINDOW = "ADD_AUTHOR_WINDOW";

    public AddAuthorUiPlugin(AddBookWindow addBookWindow) {
        super(ADD_AUTHOR_WINDOW, new AddAuthorWindow(addBookWindow));
    }
}
