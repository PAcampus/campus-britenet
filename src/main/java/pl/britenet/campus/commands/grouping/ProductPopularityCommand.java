package pl.britenet.campus.commands.grouping;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.services.grouping.ProductPopularityService;

import java.text.ParseException;
import java.util.Map;

public class ProductPopularityCommand extends Command {
    private final ProductPopularityService productPopularityService;

    public ProductPopularityCommand(ProductPopularityService productPopularityService) {
        super(Constants.COMMAND_PRODUCT_POPULARITY);
        this.productPopularityService = productPopularityService;
    }

    @Override
    public void execute() throws ParseException {
        Map<Integer, Integer> productPopularity = this.productPopularityService.getPopularityProducts();
        for(Map.Entry<Integer, Integer> entry: productPopularity.entrySet()) {
            System.out.println("ProductId: " + entry.getKey() + " Num Sold: " + entry.getValue());
        }
    }
}
