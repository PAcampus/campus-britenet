import org.junit.jupiter.api.*;
import pl.britenet.campus.database.DatabaseService;
import pl.britenet.campus.models.Product;
import pl.britenet.campus.services.ProductService;

import java.util.Date;
import java.util.List;

public class ProductServiceTests {
    private ProductService productService;
    private DatabaseService databaseService;

    @BeforeEach
    public void SetupService() {
        this.databaseService = new DatabaseService();
        this.productService = new ProductService(this.databaseService);
    }


    @Test
    @DisplayName("Tests if retrieves products")
    public void getProductsTest() {
        assert !this.productService.getProducts().isEmpty();
    }

    @Test
    @DisplayName("Tests if retrieves a single product")
    public void getProductTest() {
        assert this.productService.getProduct(1).isPresent();
    }

    @Test
    @DisplayName("Tests if correctly added product")
    public void insertProductTest() {
        List<Product> previousProducts = this.productService.getProducts();
        Product product = new Product();
        product.setName("Test");
        product.setDescription("Test");
        product.setPrice(100);
        product.setAddedAt(new Date());

        this.productService.insertProduct(product);

        assert this.productService.getProducts().size() > previousProducts.size();
    }

}
