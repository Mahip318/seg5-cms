public class YourOrder {
    private List<OrderItem> items;
    private double total;

    public void calculateTotal() {
        total = items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        }
    }
