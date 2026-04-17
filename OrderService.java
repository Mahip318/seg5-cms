import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private List<MenuItem> menu;
    private List<YourOrder> completedOrders = new ArrayList<>();

    public OrderService(List<MenuItem> menu) {
        this.menu = menu;
    }

    public void displayMenu() {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem m = menu.get(i);
            System.out.println(i + ". " + m.getName()
                    + " - $" + m.getPrice()
                    + " (Stock: " + m.getStock() + ")");
        }
    }

    public YourOrder createOrder(int index, int qty) {

        if (index < 0 || index >= menu.size()) {
            System.out.println("Invalid menu index");
            return null;
        }

        if (qty <= 0) {
            System.out.println("Quantity must be > 0");
            return null;
        }

        MenuItem item = menu.get(index);

        if (item.getStock() < qty) {
            System.out.println("Not enough stock! ");
            return null;
        }

        item.decreaseStock(qty);

        YourOrder order = new YourOrder();
        order.addItem(new OrderItem(item, qty));

        completedOrders.add(order);

        return order;
    }

    public List<YourOrder> getCompletedOrders() {
        return completedOrders;
    }
}