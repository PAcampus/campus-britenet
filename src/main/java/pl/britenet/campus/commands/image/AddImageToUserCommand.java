package pl.britenet.campus.commands.image;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.User;
import pl.britenet.campus.services.ImageService;
import pl.britenet.campus.services.UserService;

import java.text.ParseException;
import java.util.Optional;
import java.util.Scanner;

public class AddImageToUserCommand extends Command {
    private final ImageService imageService;
    private final UserService userService;

    public AddImageToUserCommand(ImageService imageService,UserService userService) {
        super(Constants.COMMAND_ADD_IMAGE_TO_USER);
        this.imageService = imageService;
        this.userService = userService;
    }

    @Override
    public void execute() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter UserId:");
        int id = scanner.nextInt();
        Optional<User> user = this.userService.getUser(id);
        user.ifPresent(value ->
                System.out.println(String.valueOf(value.getId()) + ' ' +
                        value.getName() + ' ' + value.getLast_name() + ' ' +
                        value.getEmail()));

    }
}
