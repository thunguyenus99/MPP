package presentation.searchbook;

import presentation.UiPlugin;

public class SearchBookUiPlugin extends UiPlugin {

    public static final String NAME = "SEARCH_BOOK_WINDOW";

    public SearchBookUiPlugin() {
        super(NAME, new SearchBookWindow());
    }
}
