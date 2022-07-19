package pl.britenet.campus.commands.product;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.Product;
import pl.britenet.campus.services.ProductService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EditProductCommand extends Command {
    private final ProductService productService;
    private Product product;

    public EditProductCommand(ProductService productService) {
        super(Constants.COMMAND_EDIT_PRODUCT);
        this.productService = productService;
        this.product = new Product();
    }

    @Override
    public void execute() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id:");
        int id = scanner.nextInt();
        final Product[] temp = {new Product()};
        this.productService.getProduct(id).ifPresent(value -> temp[0] = value);
        this.product = temp[0];

        System.out.println("Enter Name:");
        this.product.setName(scanner.next());

        System.out.println("Enter Description:");
        this.product.setDescription(scanner.next());

        System.out.println("Enter price:");
        this.product.setPrice(Double.parseDouble(scanner.next()));

        System.out.println("Enter date in format yyyy-MM-dd:");
        String dateString = scanner.next();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = df.parse(dateString);
        this.product.setAddedAt(myDate);

        System.out.println("UPDATING PRODUCT:");
        System.out.println("Name: " + this.product.getName() + " Description: " + this.product.getDescription()
                + " Price: " + this.product.getPrice());
        this.productService.updateProduct(this.product);
    }
}
