package pl.britenet.campus.commands.user;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.User;
import pl.britenet.campus.services.UserService;

import java.util.Optional;
import java.util.Scanner;

public class DeleteUserCommand extends Command {
    private final UserService userService;

    public DeleteUserCommand(UserService userService) {
        super(Constants.COMMAND_DELETE_USER);
        this.userService = userService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id:");
        int id = scanner.nextInt();
        System.out.println("DELETING USER:");
        Optional<User> user = this.userService.getUser(id);
        user.ifPresent(value ->
                System.out.println(String.valueOf(value.getId()) + ' ' +
                        value.getName() + ' ' + value.getLast_name() + ' ' +
                        value.getEmail()));
        this.userService.deleteUser(id);
    }
}
