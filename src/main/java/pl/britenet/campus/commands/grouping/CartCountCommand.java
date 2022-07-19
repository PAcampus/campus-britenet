package pl.britenet.campus.commands.grouping;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.services.grouping.CartCountService;

import java.text.ParseException;
import java.util.Map;

public class CartCountCommand extends Command {
    private final CartCountService cartCountService;

    public CartCountCommand(CartCountService cartCountService) {
        super(Constants.COMMAND_CART_COUNT);
        this.cartCountService = cartCountService;
    }

    @Override
    public void execute() throws ParseException {
        Map<Integer, Integer> cartCount = cartCountService.getCartCount();
        for (Map.Entry<Integer, Integer> entry: cartCount.entrySet()) {
            System.out.println("Id: " + entry.getKey() + " CartCount: " + entry.getValue());
        }
    }
}
