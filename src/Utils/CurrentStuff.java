package Utils;

import Models.User;

public class CurrentStuff {
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setUser(User user) {
        currentUser = user;
    }
}
