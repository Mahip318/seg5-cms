public class AuthService {

    private User[] users = {
        new User("admin", "1234", "MANAGER"),
        new User("cashier", "1111", "CASHIER")
    };

    public User login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) &&
                u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}
