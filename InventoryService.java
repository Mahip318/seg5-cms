import java.util.List;

public class InventoryService {

    private List<MenuItem> menu;

    public InventoryService(List<MenuItem> menu) {
        this.menu = menu;
    }

    public void displayInventory() {
        System.out.println("=== INVENTORY ===");
        for (MenuItem m : menu) {
            System.out.println(m.getName() + " - " + m.getStock());
        }
    }

    public void restock(int index, int qty) {
        if (index < 0 || index >= menu.size()) return;
        menu.get(index).increaseStock(qty);
    }
}