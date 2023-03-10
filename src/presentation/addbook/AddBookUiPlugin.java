package presentation.addbook;

import presentation.UiPlugin;

public class AddBookUiPlugin extends UiPlugin {
    public static final String ADD_BOOK_WINDOW = "ADD_BOOK_WINDOW";

    public AddBookUiPlugin() {
        super(ADD_BOOK_WINDOW, new AddBookWindow());
    }
}
