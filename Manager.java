public class Manager {
    private static User currentUser;

    public static void setSession(User user) {
        currentUser = user;
    }
    public static User getCurrentUser() {
        return currentUser;
    }
    public static void closeSession() {
        currentUser = null;
    }
}
