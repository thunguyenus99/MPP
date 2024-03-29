package presentation.validator;

import presentation.UIFrame;
import presentation.addauthor.AddAuthorWindow;
import presentation.addbook.AddBookWindow;
import presentation.addcopy.AddCopyWindow;
import presentation.addmember.AddMemberWindow;
import presentation.checkoutbook.CheckoutBookWindow;
import presentation.login.LoginWindow;
import presentation.printcheckoutrecords.PrintCheckOutRecordsWindow;
import presentation.searchbook.SearchBookWindow;

import java.util.HashMap;
import java.util.Map;

public class ValidatorFactory {
    private static final Map<Class<? extends UIFrame>, Validator> map;

    static {
        map = new HashMap<>();
        map.put(LoginWindow.class, new LoginValidator());
        map.put(AddMemberWindow.class, new AddMemberValidator());
        map.put(AddCopyWindow.class, new AddBookCopyValidator());
        map.put(AddAuthorWindow.class, new AddAuthorValidator());
        map.put(AddBookWindow.class, new AddBookValidator());
        map.put(SearchBookWindow.class, new SearchBookValidator());
        map.put(CheckoutBookWindow.class, new CheckoutBookValidator());
        map.put(PrintCheckOutRecordsWindow.class, new PrintCheckoutRecordsValidator());
    }

    public static Validator getValidator(Class<? extends UIFrame> component) {
        return map.get(component);
    }
}
