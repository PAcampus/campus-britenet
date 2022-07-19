package pl.britenet.campus.commands.order;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.Order;
import pl.britenet.campus.services.OrderService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EditOrderCommand extends Command {
    private final OrderService orderService;
    private Order order;

    public EditOrderCommand(OrderService orderService) {
        super(Constants.COMMAND_EDIT_ORDER);
        this.orderService = orderService;
        this.order = new Order();
    }

    @Override
    public void execute() throws ParseException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter id:");
        int id = scanner.nextInt();
        final Order[] temp = {new Order()};
        this.orderService.getOrder(id).ifPresent(value -> temp[0] = value);
        this.order = temp[0];

        System.out.println("Enter CartId");
        this.order.setCartId(scanner.nextInt());

        System.out.println("Enter UserId");
        this.order.setUserId(scanner.nextInt());

        System.out.println("Enter date in format yyyy-MM-dd:");
        String dateString = scanner.next();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = df.parse(dateString);
        this.order.setCreatedAt(myDate);

        System.out.println("UPDATING ORDER: ");
        System.out.println("CartId: " + this.order.getCartId() + " UserId: " + "CreatedAt: " + this.order.getCreatedAt().toString());
        this.orderService.updateOrder(this.order);
    }
}
