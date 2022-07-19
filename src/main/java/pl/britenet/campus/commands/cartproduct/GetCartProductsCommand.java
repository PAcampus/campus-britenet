package pl.britenet.campus.commands.cartproduct;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.CartProduct;
import pl.britenet.campus.services.CartProductService;
import pl.britenet.campus.utils.Paginator;

import java.text.ParseException;
import java.util.List;

public class GetCartProductsCommand extends Command {
    private final CartProductService cartProductService;

    public GetCartProductsCommand(CartProductService cartProductService) {
        super(Constants.COMMAND_GET_CARTPRODUCTS);
        this.cartProductService = cartProductService;
    }

    @Override
    public void execute() throws ParseException {
        List<CartProduct> cartProductList = this.cartProductService.getCartProducts();
        String labels = "CartProductId CartId Total Name Description Price";
        Paginator<CartProduct> paginator = new Paginator<>(cartProductList, Constants.SHORT_PAGE, labels);
        paginator.Paginate();
    }
}
