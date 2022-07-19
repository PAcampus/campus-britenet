package pl.britenet.campus.commands.order;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.Order;
import pl.britenet.campus.services.OrderService;
import pl.britenet.campus.utils.Paginator;

import java.util.List;

public class GetOrdersCommand extends Command {
    private final OrderService orderService;

    public GetOrdersCommand(OrderService orderService) {
        super(Constants.COMMAND_GET_ORDERS);
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        List<Order> orderList = this.orderService.getOrders();
        String labels = "OrderId CartId UserId CreatedAt";
        Paginator<Order> paginator = new Paginator<>(orderList, Constants.LONG_PAGE, labels);
        paginator.Paginate();
    }
}
