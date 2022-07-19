package pl.britenet.campus.commands.user;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.User;
import pl.britenet.campus.services.UserService;

import java.util.Optional;
import java.util.Scanner;

public class GetUserCommand extends Command {
    private final UserService userService;

    public GetUserCommand(UserService userService) {
        super(Constants.COMMAND_GET_USER);
        this.userService = userService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id:");
        int id = scanner.nextInt();
        Optional<User> user = this.userService.getUser(id);
        user.ifPresent(value ->
                System.out.println(String.valueOf(value.getId()) + ' ' +
                        value.getName() + ' ' + value.getLast_name() + ' ' +
                        value.getEmail()));
    }
}
