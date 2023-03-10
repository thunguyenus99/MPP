package presentation.addbook;

import presentation.UiPlugin;

public class AddBookUiPlugin extends UiPlugin {
    public static final String NAME = "ADD_BOOK_WINDOW";

    public AddBookUiPlugin() {
        super(NAME, new AddBookWindow());
    }
}
