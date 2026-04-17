import java.util.List;

public class ReportService {

    public void generateSalesReport(List<YourOrder> orders) {
        System.out.println("=== SALES REPORT ===");

        double totalRevenue = 0;

        for (YourOrder o : orders) {
            totalRevenue += o.getTotal();
        }

        System.out.println("Orders: " + orders.size());
        System.out.println("Total Revenue: $" + totalRevenue);
    }
}
