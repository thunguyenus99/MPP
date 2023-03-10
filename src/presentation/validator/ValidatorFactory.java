package presentation.validator;

import presentation.*;
import presentation.addauthor.AddAuthorWindow;
import presentation.addbook.AddBookWindow;

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
    }

    public static Validator getValidator(Class<? extends UIFrame> component) {
        return map.get(component);
    }
}
