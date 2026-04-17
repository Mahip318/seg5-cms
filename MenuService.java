import java.util.List;

public class MenuService {

    private List<MenuItem> menu;

    public MenuService(List<MenuItem> menu) {
        this.menu = menu;
    }

    public void displayMenu() {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.get(i);
            System.out.println(i + ". " + item.getName()
                    + " - $" + item.getPrice()
                    + " (Stock: " + item.getStock() + ")");
        }
    }

    public void addItem(String name, double price, int stock) {
        menu.add(new MenuItem(name, price, stock));
        System.out.println("Item added: " + name);
    }

    public void updateItem(int index, String name, double price, int stock) {
        if (index < 0 || index >= menu.size()) return;

        MenuItem item = menu.get(index);

        item.setName(name);
        item.setPrice(price);
        item.setStock(stock);

        System.out.println("Item updated: " + name);
    }

    public void deleteItem(int index) {
        if (index < 0 || index >= menu.size()) return;

        System.out.println("Removed: " + menu.get(index).getName());
        menu.remove(index);
    }
}