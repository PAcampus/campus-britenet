package pl.britenet.campus.commands.orderproduct;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.OrderProduct;
import pl.britenet.campus.services.OrderProductService;
import pl.britenet.campus.utils.Paginator;

import java.text.ParseException;
import java.util.List;

public class GetOrderProductCommand extends Command {
    private final OrderProductService orderProductService;

    public GetOrderProductCommand(OrderProductService orderProductService) {
        super(Constants.COMMAND_GET_ORDERPRODUCTS);
        this.orderProductService = orderProductService;
    }

    @Override
    public void execute() throws ParseException {
        List<OrderProduct> orderProductList = this.orderProductService.getOrderProducts();
        String labels = "CreatedAt OrderId ProductId Name Description Price";
        Paginator<OrderProduct> paginator = new Paginator<>(orderProductList, Constants.LONG_PAGE, labels);
        paginator.Paginate();
    }
}
