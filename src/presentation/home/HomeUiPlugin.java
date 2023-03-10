package presentation.home;

import presentation.UiPlugin;

public class HomeUiPlugin extends UiPlugin {

    public static final String NAME = "HOME_WINDOW";

    public HomeUiPlugin() {
        super(NAME, new HomeWindow());
    }
}
