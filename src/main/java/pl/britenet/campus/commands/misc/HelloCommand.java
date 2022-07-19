package pl.britenet.campus.commands.misc;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;

public class HelloCommand extends Command {

    public HelloCommand() {
        super(Constants.COMMAND_HELLO);
    }

    @Override
    public void execute() {
        System.out.println("Hello World");
    }
}
