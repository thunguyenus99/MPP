package business.validation;

import presentation.AddMemberWindow;
import presentation.AddCopyWindow;
import presentation.LoginWindow;
import presentation.UIFrame;

import java.util.HashMap;
import java.util.Map;

public class ValidationFactory {
    private static Map<Class<? extends UIFrame>, Validation> map;

    static {
        map = new HashMap<>();
        map.put(LoginWindow.class, new LoginValidation());
        map.put(AddMemberWindow.class, new AddMemberValidation());
        map.put(AddCopyWindow.class, new AddBookCopyValidation());
    }

    public static Validation getValidation(Class<? extends UIFrame> component) {
        return map.get(component);
    }
}
