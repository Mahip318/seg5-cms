import java.util.ArrayList;
import java.util.List;

public class YourOrder {

    private List<OrderItem> items = new ArrayList<>();
    private double total;

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double calculateTotal() {
        total = 0;
        for (OrderItem item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    public void displayOrder() {
        for (OrderItem item : items) {
            System.out.println(
                item.getItem().getName() +
                " x" + item.getQuantity() +
                " = $" + (item.getPrice() * item.getQuantity())
            );
        }
        System.out.println("TOTAL: $" + calculateTotal());
    }
}
