//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String BARISTA_PIN = "1234";
    private static final String MANAGER_PIN = "0000";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderService orderService = new OrderService();

        System.out.println("=== TEA05 CAFE SYSTEM PROTOTYPE ===");
        boolean running = true;

        while (running) {
            System.out.println("\nEnter valid Barista PIN or Manager PIN to continue.");
            System.out.println("Type EXIT to close the application.");
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine().trim();

            if (pin.equalsIgnoreCase("EXIT")) {
                running = false;
                System.out.println("Exiting application. Goodbye!");
            } else if (pin.equals(BARISTA_PIN)) {
                runBaristaFlow(orderService, scanner);
            } else if (pin.equals(MANAGER_PIN)) {
                runManagerFlow(orderService, scanner);
            } else {
                System.out.println("Incorrect PIN. Access Denied.");
            }
        }

        scanner.close();
    }

    private static void runBaristaFlow(OrderService orderService, Scanner scanner) {
        System.out.println("Welcome, Barista. You can take orders now.");
        List<CartItem> cart = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\nBarista Menu:");
            System.out.println("1. Show Menu (see Milk Tea and out-of-stock items)");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. Complete Order");
            System.out.println("4. Cancel Order");
            System.out.println("5. Delete Sales");
            System.out.println("6. Logout");
            System.out.print("Choose an action: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    orderService.displayMenu();
                    break;
                case "2":
                    orderService.displayMenu();
                    addItemToCart(orderService, cart, scanner);
                    break;
                case "3":
                    completeOrder(orderService, cart);
                    break;
                case "4":
                    cancelOrder(cart);
                    break;
                case "5":
                    System.out.println("Access Denied: Barista cannot delete sales.");
                    break;
                case "6":
                    running = false;
                    System.out.println("Logging out... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    private static void runManagerFlow(OrderService orderService, Scanner scanner) {
        System.out.println("Welcome, Manager.");
        boolean running = true;

        while (running) {
            System.out.println("\nManager Menu:");
            System.out.println("1. Daily Sales Board");
            System.out.println("2. Show Menu");
            System.out.println("3. Logout");
            System.out.print("Choose an action: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    orderService.displaySalesBoard();
                    break;
                case "2":
                    orderService.displayMenu();
                    break;
                case "3":
                    running = false;
                    System.out.println("Manager logged out.");
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    private static void addItemToCart(OrderService orderService, List<CartItem> cart, Scanner scanner) {
        System.out.print("Select item number: ");
        String itemInput = scanner.nextLine().trim();
        int itemIndex;

        try {
            itemIndex = Integer.parseInt(itemInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return;
        }

        if (itemIndex < 0 || itemIndex >= orderService.getMenu().size()) {
            System.out.println("Invalid item selected.");
            return;
        }

        MenuItem item = orderService.getMenu().get(itemIndex);
        if (item.getStock() == 0) {
            System.out.println("Cannot add item: " + item.getName() + " is out of stock.");
            return;
        }

        System.out.print("Enter quantity: ");
        String quantityInput = scanner.nextLine().trim();
        int quantity;
        try {
            quantity = Integer.parseInt(quantityInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Quantity must be at least 1.");
            return;
        }

        if (!orderService.canSell(itemIndex, quantity)) {
            System.out.println("Not enough stock for " + item.getName() + ".");
            return;
        }

        cart.add(new CartItem(itemIndex, item, quantity));
        System.out.println(quantity + " x " + item.getName() + " added to cart.");
        displayCart(cart);
    }

    private static void completeOrder(OrderService orderService, List<CartItem> cart) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        double total = 0.0;
        for (CartItem cartItem : cart) {
            MenuItem item = orderService.getMenu().get(cartItem.getIndex());
            total += item.getPrice() * cartItem.getQuantity();
            orderService.processSale(cartItem.getIndex(), cartItem.getQuantity());
        }

        System.out.printf("Order complete. Total sale amount: $%.2f%n", total);
        cart.clear();
    }

    private static void cancelOrder(List<CartItem> cart) {
        if (cart.isEmpty()) {
            System.out.println("No active order to cancel.");
            return;
        }
        cart.clear();
        System.out.println("Order canceled.");
    }

    private static void displayCart(List<CartItem> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is currently empty.");
            return;
        }
        System.out.println("Current cart:");
        double total = 0.0;
        for (CartItem cartItem : cart) {
            double itemTotal = cartItem.getItem().getPrice() * cartItem.getQuantity();
            total += itemTotal;
            System.out.printf("- %s x%d = $%.2f%n", cartItem.getItem().getName(), cartItem.getQuantity(), itemTotal);
        }
        System.out.printf("Cart total: $%.2f%n", total);
    }

    private static class CartItem {
        private final int index;
        private final MenuItem item;
        private final int quantity;

        public CartItem(int index, MenuItem item, int quantity) {
            this.index = index;
            this.item = item;
            this.quantity = quantity;
        }

        public int getIndex() {
            return index;
        }

        public MenuItem getItem() {
            return item;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}