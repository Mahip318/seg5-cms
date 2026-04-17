import java.util.List;

public class AuthService {

    private List<User> users = List.of(
            new User("Manager", "Boba1234", Role.Manager),
            new User("Cashier", "Tea1122", Role.Cashier)
    );

    public User login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username)
                    && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}