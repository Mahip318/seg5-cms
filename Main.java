//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        OrderService pos = new OrderService();

        System.out.println("=== TEA05 CAFE SYSTEM PROTOTYPE ===");
        
        // Phase 1: Authentication (Simplified)
        System.out.print("Enter Barista PIN: ");
        String pin = scanner.nextLine();
        
        if (!pin.equals("1234")) {
            System.out.println("Access Denied.");
            return;
        }

        // Phase 2: Order Selection Journey
        boolean running = true;
        while (running) {
            System.out.println("\n--- Current Menu ---");
            pos.displayMenu();
            System.out.print("Select Item # to Sell (or -1 to Logout): ");
            int choice = scanner.nextInt();

            if (choice == -1) {
                running = false;
                System.out.println("Logging out... Goodbye!");
            } else if (choice >= 0 && choice < pos.getMenu().size()) {
                pos.processSale(choice);
            } else {
                System.out.println("Invalid Selection.");
            }
        }
        scanner.close();
    }
}