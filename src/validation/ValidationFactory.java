package validation;

import view.LoginPanel;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ValidationFactory {
    private static Map<Class<? extends Component>, Validation> map;
    static {
        map = new HashMap<>();
        map.put(LoginPanel.class, new LoginValidation());
    }

    public static Validation getValidation(Class<? extends Component> component) {
        return map.get(component);
    }
}
