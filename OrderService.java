import java.util.ArrayList;
import java.util.List;

public class OrderService {
    // This serves as our "Hardcoded Database"
    private List<MenuItem> database = new ArrayList<>();

    public OrderService() {
        // Seed the "Database" with Tea05 menu items
        database.add(new MenuItem("Taro Milk Tea", 5.50, 10));
        database.add(new MenuItem("Matcha Latte", 6.00, 5));
        database.add(new MenuItem("Thai Ice Tea", 4.75, 15));
        database.add(new MenuItem("Brown Sugar Boba", 6.50, 8));
    }

    public List<MenuItem> getMenu() {
        return database;
    }

    public void displayMenu() {
        for (int i = 0; i < database.size(); i++) {
            MenuItem item = database.get(i);
            System.out.println(i + ". " + item.getName() + " - $" + item.getPrice() + " (Stock: " + item.getStock() + ")");
        }
    }

    public void processSale(int index) {
        MenuItem p = database.get(index);
        if (p.getStock() > 0) {
            p.reduceStock(1);
            System.out.println("Sale Successful! Stock updated for " + p.getName());
        } else {
            System.out.println("CRITICAL: Out of stock!");
        }
    }
}