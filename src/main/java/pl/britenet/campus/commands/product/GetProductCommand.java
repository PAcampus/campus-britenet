package pl.britenet.campus.commands.product;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.Product;
import pl.britenet.campus.services.ProductService;

import java.util.Optional;
import java.util.Scanner;

public class GetProductCommand extends Command {
    private final ProductService productService;

    public GetProductCommand(ProductService productService) {
        super(Constants.COMMAND_GET_PRODUCT);
        this.productService = productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id:");
        int id = scanner.nextInt();
        Optional<Product> product = this.productService.getProduct(id);
        product.ifPresent(value ->
                System.out.println(String.valueOf(value.getId()) + ' ' + value.getName() + ' ' + value.getDescription()));
    }
}
