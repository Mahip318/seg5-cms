public class InventoryService {

  private List<MenuItem> inventory = new ArrayList<>();

  public InventoryService() {
    inventory.add(new MenuItem("Taro Milk Tea", 5.50, 10));
    inventory.add(new MenuItem("Matcha Latte", 6.00, 5));
    inventory.add(new MenuItem("Thai Ice Tea", 4.75, 15));
    inventory.add(new MenuItem("Brown Sugar Boba", 6.50, 8));
  //Retrieve all inventory 
  public List<MenuItem> getInventory() {
    return inventory;
  }
 //Display inventory list
  public void displayInventory() {
    for (int i = 0; i < inventory.size(); i++) {
            MenuItem item = inventory.get(i);
            System.out.println(i + ". " + item.getName() +
                    " (Stock: " + item.getStock() + ")");
        }
    }
 //Stock is updated manually 
  public void updateStock(int index, int newStock) {
        MenuItem item = inventory.get(index);
        item.setStock(newStock);

        if (newStock < 5) {
            System.out.println("WARNING: Low stock for " + item.getName());
        } else {
            System.out.println("Stock updated for " + item.getName());
        }
    }
    //Reduce stock when an order is made
    public void reduceStock(int index, int quantity) {
        MenuItem item = inventory.get(index);

        if (item.getStock() >= quantity) {
            item.reduceStock(quantity);
            System.out.println("Stock reduced for " + item.getName());
        } else {
            System.out.println("ERROR: Not enough stock for " + item.getName());
        }
    }
   // Low stock check (automatic trigger)
    public void checkLowStock() {
        for (MenuItem item : inventory) {
            if (item.getStock() < 5) {
                System.out.println("LOW STOCK ALERT: " + item.getName());
            }
        }
    }
}

