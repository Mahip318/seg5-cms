import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private List<MenuItem> database = new ArrayList<>();

    public MenuService() {
        //Seed menu items (same as OrderService for consistency)
        database.add(new MenuItem("Taro Milk Tea", 5.50, 10));
        database.add(new MenuItem("Matcha Latte", 6.00, 5));
        database.add(new MenuItem("Thai Ice Tea", 4.75, 15));
        database.add(new MenuItem("Brown Sugar Boba", 6.50, 8));
    }

    //Retrieve full menu
    public List<MenuItem> getMenu() {
        return database;
    }

    //Display menu
    public void displayMenu() {
        for (int i = 0; i < database.size(); i++) {
            MenuItem item = database.get(i);
            System.out.println(i + ". " + item.getName() +
                    " - $" + item.getPrice() +
                    " (Stock: " + item.getStock() + ")");
        }
    }

    //Add a new item (Manager)
    public void addItem(String name, double price, int stock) {
        database.add(new MenuItem(name, price, stock));
        System.out.println("Item added: " + name);
    }

    //Update an item (Manager)
    public void updateItem(int index, String name, double price, int stock) {
        MenuItem item = database.get(index);
        item.setName(name);
        item.setPrice(price);
        item.setStock(stock);

        System.out.println("Item updated: " + name);
    }

    //Delete an item (Manager)
    public void deleteItem(int index) {
        MenuItem removed = database.remove(index);
        System.out.println("Item removed: " + removed.getName());
    }
}
