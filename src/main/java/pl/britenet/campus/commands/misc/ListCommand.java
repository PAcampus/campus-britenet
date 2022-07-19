package pl.britenet.campus.commands.misc;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.utils.Paginator;

import java.util.List;

public class ListCommand extends Command {
    private final List<Command> commandList;
    public ListCommand(List<Command> commandList) {
        super(Constants.COMMAND_LIST);
        this.commandList = commandList;
    }

    @Override
    public void execute() {
        Paginator<Command> paginator = new Paginator<>(commandList, Constants.VERY_LONG_PAGE, "--Commands--");
        paginator.Paginate();
    }
}
