import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<MenuItem> database = new ArrayList<>();
    private double dailyRevenue = 0.0;
    private int dailySalesCount = 0;
    private int totalItemsSold = 0;
    private int[] itemQuantitiesSold;
    private double[] itemSalesRevenue;

    public OrderService() {
        database.add(new MenuItem("Milk Tea", 10.00, 10));
        database.add(new MenuItem("Taro Milk Tea", 5.50, 10));
        database.add(new MenuItem("Matcha Latte", 6.00, 10));
        database.add(new MenuItem("Thai Ice Tea", 4.75, 15));
        database.add(new MenuItem("Brown Sugar Boba", 6.50, 8));
        database.add(new MenuItem("Black Tea", 4.00, 0));
        itemQuantitiesSold = new int[database.size()];
        itemSalesRevenue = new double[database.size()];
    }

    public List<MenuItem> getMenu() {
        return database;
    }

    public void displayMenu() {
        System.out.println("Available Menu Items:");
        for (int i = 0; i < database.size(); i++) {
            MenuItem item = database.get(i);
            String stockText = item.getStock() == 0 ? "OUT OF STOCK" : "Stock: " + item.getStock();
            System.out.println(i + ". " + item.getName() + " - $" + item.getPrice() + " (" + stockText + ")");
        }
    }

    public boolean canSell(int index, int quantity) {
        if (index < 0 || index >= database.size()) {
            return false;
        }
        MenuItem item = database.get(index);
        return item.getStock() >= quantity;
    }

    public void processSale(int index, int quantity) {
        MenuItem item = database.get(index);
        if (quantity <= 0) {
            System.out.println("ERROR: Quantity must be at least 1.");
            return;
        }

        if (item.getStock() >= quantity) {
            item.reduceStock(quantity);
            double orderValue = item.getPrice() * quantity;
            dailyRevenue += orderValue;
            dailySalesCount += 1;
            totalItemsSold += quantity;
            itemQuantitiesSold[index] += quantity;
            itemSalesRevenue[index] += orderValue;
            System.out.printf("Sale Successful! %d x %s sold for $%.2f.%n", quantity, item.getName(), orderValue);
        } else {
            System.out.println("CRITICAL: Out of stock or insufficient quantity!");
        }
    }

    public void displayDailySales() {
        displaySalesBoard();
    }

    public void displaySalesBoard() {
        System.out.println("=== Manager Sales Board ===");
        System.out.println("Total transactions: " + dailySalesCount);
        System.out.println("Total items sold: " + totalItemsSold);
        System.out.printf("Total revenue: $%.2f%n", dailyRevenue);
        System.out.println("Item-level sales:");
        for (int i = 0; i < database.size(); i++) {
            MenuItem item = database.get(i);
            String status = item.getStock() == 0 ? "OUT OF STOCK" : "Stock: " + item.getStock();
            System.out.printf("- %s: sold %d, revenue $%.2f, %s%n",
                item.getName(), itemQuantitiesSold[i], itemSalesRevenue[i], status);
        }
    }

    public void resetSales() {
        dailyRevenue = 0.0;
        dailySalesCount = 0;
        totalItemsSold = 0;
        for (int i = 0; i < itemQuantitiesSold.length; i++) {
            itemQuantitiesSold[i] = 0;
            itemSalesRevenue[i] = 0.0;
        }
    }
}