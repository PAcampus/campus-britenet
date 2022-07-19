package pl.britenet.campus.commands.cart;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.Cart;
import pl.britenet.campus.services.CartService;

import java.util.Scanner;

public class EditCartCommand extends Command {
    private Cart cart;
    private final CartService cartService;

    public EditCartCommand(CartService cartService) {
        super(Constants.COMMAND_EDIT_CART);
        this.cartService = cartService;
        this.cart = new Cart();
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter id:");
        int id = scanner.nextInt();
        final Cart[] temp = {new Cart()};
        this.cartService.getCart(id).ifPresent(value -> temp[0] = value);
        this.cart = temp[0];

        System.out.println("Enter UserId:");
        this.cart.setUserId(scanner.nextInt());

        System.out.println("Enter Total:");
        this.cart.setTotal(Double.parseDouble(scanner.next()));

        System.out.println("INSERTING CART:");
        System.out.println("UserId: " + this.cart.getUserId() + " Total: " + this.cart.getTotal());
        this.cartService.updateCart(this.cart);
    }
}
