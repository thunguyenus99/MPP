package presentation.printcheckoutrecords;

import presentation.UiPlugin;

public class PrintCheckoutRecordsUiPlugin extends UiPlugin {

    public static final String NAME = "PRINT_CHECKOUT_RECORDS_WINDOW";

    public PrintCheckoutRecordsUiPlugin() {
        super(NAME, new PrintCheckOutRecordsWindow());
    }
}
