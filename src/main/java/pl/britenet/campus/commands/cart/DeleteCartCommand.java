package pl.britenet.campus.commands.cart;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.Cart;
import pl.britenet.campus.services.CartService;

import java.util.Optional;
import java.util.Scanner;

public class DeleteCartCommand extends Command {
    private final CartService cartService;

    public DeleteCartCommand(CartService cartService) {
        super(Constants.COMMAND_DELETE_CART);
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id:");
        int id = scanner.nextInt();

        System.out.println("DELETING CART:");
        Optional<Cart> cart = this.cartService.getCart(id);
        cart.ifPresent(value ->
                System.out.println(String.valueOf(value.getId()) + ' ' +
                        String.valueOf(value.getTotal()) + ' ' +
                        String.valueOf(value.getUserId())));
        this.cartService.deleteCart(id);
    }
}
