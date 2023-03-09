package business.validation;

import presentation.LoginWindow;
import presentation.UIFrame;

import java.util.HashMap;
import java.util.Map;

public class ValidationFactory {
    private static Map<Class<? extends UIFrame>, Validation> map;

    static {
        map = new HashMap<>();
        map.put(LoginWindow.class, new LoginValidation());
    }

    public static Validation getValidation(Class<? extends UIFrame> component) {
        return map.get(component);
    }
}
