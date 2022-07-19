package pl.britenet.campus;

import pl.britenet.campus.commands.*;
import pl.britenet.campus.commands.cart.*;
import pl.britenet.campus.commands.cartproduct.GetCartProductsCommand;
import pl.britenet.campus.commands.grouping.*;
import pl.britenet.campus.commands.misc.ExitCommand;
import pl.britenet.campus.commands.misc.HelloCommand;
import pl.britenet.campus.commands.misc.ListCommand;
import pl.britenet.campus.commands.order.*;
import pl.britenet.campus.commands.orderproduct.GetOrderProductCommand;
import pl.britenet.campus.commands.product.*;
import pl.britenet.campus.commands.user.*;
import pl.britenet.campus.database.DatabaseService;
import pl.britenet.campus.models.OrderProduct;
import pl.britenet.campus.services.*;
import pl.britenet.campus.services.grouping.*;

import java.text.ParseException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        boolean isRunning = true;

        DatabaseService databaseService = new DatabaseService();
        ProductService productService = new ProductService(databaseService);
        OrderService orderService = new OrderService(databaseService);
        UserService userService = new UserService(databaseService);
        CartService cartService = new CartService(databaseService);
        CartCountService cartCountService = new CartCountService(databaseService);
        MonthlySalesService monthlySalesService = new MonthlySalesService(databaseService);
        ProductPopularityService productPopularityService = new ProductPopularityService(databaseService);
        TotalSpentByUserService totalSpentByUserService = new TotalSpentByUserService(databaseService);
        UserStatusService userStatusService = new UserStatusService(databaseService);
        CartProductService cartProductService = new CartProductService(databaseService);
        OrderProductService orderProductService = new OrderProductService(databaseService);

        CommandService commandService = new CommandService();
        registerCommands(productService, orderService, userService, cartService,
                cartCountService, monthlySalesService, productPopularityService,
                totalSpentByUserService, userStatusService, cartProductService, orderProductService,
                commandService);

        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            System.out.println("Enter command...");
            String command = scanner.nextLine();
            Optional<Command> oCommand = commandService.getCommand(command);
            oCommand.orElseThrow().execute();
        }
    }

    private static void registerCommands(ProductService productService, OrderService orderService,
                                         UserService userService, CartService cartService,
                                         CartCountService cartCountService, MonthlySalesService monthlySalesService,
                                         ProductPopularityService productPopularityService, TotalSpentByUserService totalSpentByUserService,
                                         UserStatusService userStatusService, CartProductService cartProductService,
                                         OrderProductService orderProductService, CommandService commandService) {
        commandService.registerCommand(new HelloCommand());
        commandService.registerCommand(new ExitCommand());
        commandService.registerCommand(new ListCommand(commandService.GetCommandList()));

        commandService.registerCommand(new GetCartsCommand(cartService));
        commandService.registerCommand(new GetCartCommand(cartService));
        commandService.registerCommand(new AddCartCommand(cartService));
        commandService.registerCommand(new DeleteCartCommand(cartService));
        commandService.registerCommand(new EditCartCommand(cartService));

        commandService.registerCommand(new GetProductsCommand(productService));
        commandService.registerCommand(new GetProductCommand(productService));
        commandService.registerCommand(new AddProductCommand(productService));
        commandService.registerCommand(new DeleteProductCommand(productService));
        commandService.registerCommand(new EditProductCommand(productService));

        commandService.registerCommand(new GetUserCommand(userService));
        commandService.registerCommand(new GetUsersCommand(userService));
        commandService.registerCommand(new AddUserCommand(userService));
        commandService.registerCommand(new DeleteUserCommand(userService));
        commandService.registerCommand(new EditUserCommand(userService));

        commandService.registerCommand(new GetOrdersCommand(orderService));
        commandService.registerCommand(new GetOrderCommand(orderService));
        commandService.registerCommand(new AddOrderCommand(orderService));
        commandService.registerCommand(new DeleteOrderCommand(orderService));
        commandService.registerCommand(new EditOrderCommand(orderService));

        commandService.registerCommand(new CartCountCommand(cartCountService));
        commandService.registerCommand(new MonthlySalesCommand(monthlySalesService));
        commandService.registerCommand(new ProductPopularityCommand(productPopularityService));
        commandService.registerCommand(new TotalSpentByUserCommand(totalSpentByUserService));
        commandService.registerCommand(new UserStatusCommand(userStatusService));

        commandService.registerCommand(new GetCartProductsCommand(cartProductService));
        commandService.registerCommand(new GetOrderProductCommand(orderProductService));
    }
}
