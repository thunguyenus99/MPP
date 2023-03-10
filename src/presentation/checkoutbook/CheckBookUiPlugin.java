package presentation.checkoutbook;

import presentation.UiPlugin;

public class CheckBookUiPlugin extends UiPlugin {

    public static final String NAME = "CHECKOUT_BOOK_WINDOW";

    public CheckBookUiPlugin() {
        super(NAME, new CheckoutBookWindow());
    }
}
