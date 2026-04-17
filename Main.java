import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<MenuItem> menu = new ArrayList<>();
        menu.add(new MenuItem("Milk Tea", 10.0, 10));
        menu.add(new MenuItem("Taro Milk Tea", 10.0, 10));
        menu.add(new MenuItem("Matcha Latte", 10.0, 10));
        menu.add(new MenuItem("Thai Ice Tea", 10.0, 10));
        menu.add(new MenuItem("Brown Sugar Boba", 10.0, 10));
        menu.add(new MenuItem("Black Tea", 10.0, 10));


        AuthService auth = new AuthService();
        OrderService orderService = new OrderService(menu);
        InventoryService inventory = new InventoryService(menu);
        ReportService report = new ReportService();

        System.out.println("LOGIN:");
        String u = sc.nextLine();
        System.out.println("PASSWORD: ");
        String p = sc.nextLine();

        User user = auth.login(u, p);

        if (user == null) {
            System.out.println("Invalid login");
            return;
        }

        Manager.login(user);

        System.out.println("Welcome " + user.getUsername());

        boolean running = true;

        while (running) {

            System.out.println("\n1. Sell item");
            System.out.println("2. Inventory");
            System.out.println("3. Report");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // ✅ FIX IMPORTANT

            switch (choice) {

                case 1 -> {
                    orderService.displayMenu();

                    System.out.print("Item: ");
                    int i = sc.nextInt();
                    sc.nextLine(); // ✅ FIX

                    System.out.print("Qty: ");
                    int q = sc.nextInt();
                    sc.nextLine(); // ✅ FIX

                    YourOrder order = orderService.createOrder(i, q);

                    if (order != null)
                        System.out.println("Total: $" + order.getTotal());
                    else
                        System.out.println("Failed sale");
                }

                case 2 -> inventory.displayInventory();

                case 3 -> report.generateSalesReport(orderService.getCompletedOrders());

                case 4 -> running = false;
            }
        }

        sc.close();
    }
}