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

public class AddProductCommand extends Command {
    private final ProductService productService;
    private Product product;

    public AddProductCommand(ProductService productService) {
        super(Constants.COMMAND_ADD_PRODUCT);
        this.productService = productService;
        this.product = new Product();
    }

    @Override
    public void execute() throws ParseException {
        Scanner scanner = new Scanner(System.in);

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

        System.out.println("INSERTING PRODUCT:");
        System.out.println("Name: " + this.product.getName() + " Description: " + this.product.getDescription()
                + " Price: " + this.product.getPrice());
        this.productService.insertProduct(this.product);
    }
}
