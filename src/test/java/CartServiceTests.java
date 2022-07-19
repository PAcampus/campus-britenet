import org.junit.jupiter.api.*;
import pl.britenet.campus.database.DatabaseService;
import pl.britenet.campus.services.CartService;


public class CartServiceTests {
    private CartService cartService;
    private DatabaseService databaseService;

    @BeforeEach
    public void setupService() {
        this.databaseService = new DatabaseService();
        this.cartService = new CartService(this.databaseService);
    }

    @Test
    @DisplayName("Tests if retrieves carts")
    public void getCartsTest() {
        assert !this.cartService.getCarts().isEmpty();
    }

    @Test
    @DisplayName("Tests if retrieves a single cart")
    public void getCartTest() {
        assert this.cartService.getCart(1).isPresent();
    }
}
