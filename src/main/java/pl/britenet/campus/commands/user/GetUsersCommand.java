package pl.britenet.campus.commands.user;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.models.User;
import pl.britenet.campus.services.UserService;
import pl.britenet.campus.utils.Paginator;

import java.util.List;

public class GetUsersCommand extends Command {
    private final UserService userService;

    public GetUsersCommand(UserService userService) {
        super(Constants.COMMAND_GET_USERS);
        this.userService = userService;
    }

    @Override
    public void execute() {
        List<User> userList = this.userService.getUsers();
        String labels = "UserId Name Last_name";
        Paginator<User> paginator = new Paginator<>(userList, Constants.LONG_PAGE, labels);
        paginator.Paginate();
    }
}
