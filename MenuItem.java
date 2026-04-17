public class MenuItem {
    private String name;
    private double price;
    private int stock;

    public MenuItem(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    // ✅ FIX: required setters (your code needed these)
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void decreaseStock(int qty) {
        if (qty <= 0 || qty > stock) return;
        stock -= qty;
    }

    public void increaseStock(int qty) {
        if (qty <= 0) return;
        stock += qty;
    }
}