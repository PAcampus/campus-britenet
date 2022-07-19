package pl.britenet.campus.commands.cart;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.Cart;
import pl.britenet.campus.services.CartService;
import pl.britenet.campus.utils.Paginator;

import java.util.List;

public class GetCartsCommand extends Command {
    private final CartService cartService;

    public GetCartsCommand(CartService cartService) {
        super(Constants.COMMAND_GET_CARTS);
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        List<Cart> cartList = this.cartService.getCarts();
        String labels = "CartId UserId Total";
        Paginator<Cart> paginator = new Paginator<>(cartList, Constants.LONG_PAGE, labels);
        paginator.Paginate();
    }
}
