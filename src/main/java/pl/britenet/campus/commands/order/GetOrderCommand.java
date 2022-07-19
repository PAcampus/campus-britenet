package pl.britenet.campus.commands.order;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.Order;
import pl.britenet.campus.services.OrderService;

import java.util.Optional;
import java.util.Scanner;

public class GetOrderCommand extends Command {
    private final OrderService orderService;

    public GetOrderCommand(OrderService orderService) {
        super(Constants.COMMAND_GET_ORDER);
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id:");
        int id = scanner.nextInt();
        Optional<Order> order = this.orderService.getOrder(id);
        order.ifPresent(value ->
                System.out.println(String.valueOf(value.getId()) + ' ' +
                        String.valueOf(value.getCartId()) + ' ' + value.getCreatedAt().toString()));
    }
}
