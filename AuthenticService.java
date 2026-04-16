import static org.junit.Assert.*;
import org.junit.Test;

public class Tea05Test {
    
    @Test
    public void testStockReduction() {
        OrderManager om = new OrderManager();
        int initialStock = om.getMenu().get(0).getStock(); // 10
        om.processSale(0);
        assertEquals(initialStock - 1, om.getMenu().get(0).getStock());
    }

    @Test
    public void testOutOfStockWarning() {
        // Create a product with 0 stock
        Product p = new Product("Empty Tea", 5.0, 0);
        assertTrue(p.getStock() == 0);
    }
}

// public class AuthenticService {
//     public boolean login(String username, String password) {
//         User user = 
//     }
// }
