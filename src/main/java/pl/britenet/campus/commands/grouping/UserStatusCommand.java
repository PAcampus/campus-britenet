package pl.britenet.campus.commands.grouping;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.services.grouping.UserStatusService;

import java.text.ParseException;
import java.util.Map;

public class UserStatusCommand extends Command {
    private final UserStatusService userStatusService;

    public UserStatusCommand(UserStatusService userStatusService) {
        super(Constants.COMMAND_USER_STATUS);
        this.userStatusService = userStatusService;
    }

    @Override
    public void execute() throws ParseException {
        Map<Integer, String> userStatus = this.userStatusService.getUserStatus();
        for (Map.Entry<Integer, String> entry: userStatus.entrySet()) {
            System.out.println("UserId: " + entry.getKey() + " Status: " + entry.getValue());
        }
    }
}
