package pl.britenet.campus.commands.order;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.Order;
import pl.britenet.campus.services.OrderService;

import java.util.Optional;
import java.util.Scanner;

public class DeleteOrderCommand extends Command {
    private final OrderService orderService;

    public DeleteOrderCommand(OrderService orderService) {
        super(Constants.COMMAND_DELETE_ORDER);
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id:");
        int id = scanner.nextInt();
        System.out.println("DELETING ORDER:");
        Optional<Order> order = this.orderService.getOrder(id);
        order.ifPresent(value ->
                System.out.println(String.valueOf(value.getId()) + ' ' +
                        String.valueOf(value.getCartId()) + ' ' + value.getCreatedAt().toString()));
        this.orderService.deleteOrder(id);
    }
}
