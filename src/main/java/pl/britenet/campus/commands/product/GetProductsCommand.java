package pl.britenet.campus.commands.product;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.Product;
import pl.britenet.campus.services.ProductService;
import pl.britenet.campus.utils.Paginator;

import java.util.List;

public class GetProductsCommand extends Command {
    private final ProductService productService;

    public GetProductsCommand(ProductService productService) {
        super(Constants.COMMAND_GET_PRODUCTS);
        this.productService = productService;
    }

    @Override
    public void execute() {
        List<Product> productList = this.productService.getProducts();
        String labels = "ProductId Name Description Price AddedAt";
        Paginator<Product> paginator = new Paginator<>(productList, Constants.LONG_PAGE, labels);
        paginator.Paginate();
    }
}
