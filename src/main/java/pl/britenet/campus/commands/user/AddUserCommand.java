package pl.britenet.campus.commands.user;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.User;
import pl.britenet.campus.services.UserService;

import java.util.Scanner;

public class AddUserCommand extends Command {
    private final UserService userService;
    private User user;

    public AddUserCommand(UserService userService) {
        super(Constants.COMMAND_ADD_USER);
        this.userService = userService;
        this.user = new User();
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Name:");
        this.user.setName(scanner.nextLine());

        System.out.println("Enter Last_name:");
        this.user.setLast_name(scanner.nextLine());

        System.out.println("Enter Address");
        this.user.setAddress(scanner.nextLine());

        System.out.println("Enter Email");
        this.user.setEmail(scanner.nextLine());

        System.out.println("Enter password:");
        this.user.setPassword(scanner.nextLine());

        System.out.println("Enter Phone_number:");
        this.user.setPhone_number(scanner.nextLine());

        System.out.println("INSERTING USER:");
        System.out.println("Name: " + this.user.getName() + " Last_name: " + this.user.getLast_name() + " Email: " + this.user.getEmail());
        this.userService.insertUser(this.user);
    }
}
