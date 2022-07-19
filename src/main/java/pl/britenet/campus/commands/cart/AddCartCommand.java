package pl.britenet.campus.commands.cart;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.Cart;
import pl.britenet.campus.services.CartService;

import java.util.Scanner;

public class AddCartCommand extends Command {
    private final CartService cartService;
    private Cart cart;

    public AddCartCommand(CartService cartService) {
        super(Constants.COMMAND_ADD_CART);
        this.cartService = cartService;
        this.cart = new Cart();
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter UserId:");
        this.cart.setUserId(scanner.nextInt());

        System.out.println("Enter Total:");
        this.cart.setTotal(Double.parseDouble(scanner.next()));

        System.out.println("INSERTING CART:");
        System.out.println("UserId: " + this.cart.getUserId() + " Total: " + this.cart.getTotal());
        this.cartService.insertCart(this.cart);
    }
}
