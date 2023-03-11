package business;

import data.model.User;

public class LoggedInUser {
    private static User user;

    public static User get() {
        return LoggedInUser.user;
    }

    public static void set(User u) {
        user = u;
    }

    private LoggedInUser() {

    }
}
