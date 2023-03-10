package presentation.login;

import presentation.UiPlugin;

public class LoginUiPlugin extends UiPlugin {

    public static final String NAME = "LOGIN_WINDOW";

    public LoginUiPlugin() {
        super(NAME, new LoginWindow());
    }
}
