package pl.britenet.campus.commands.misc;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(Constants.COMMAND_EXIT);
    }

    @Override
    public void execute() {
        System.out.println("--Exit--");
        System.exit(0);
    }
}
