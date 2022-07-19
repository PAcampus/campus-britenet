package pl.britenet.campus.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandService {
    private final List<Command> commandList;

    public CommandService() {
        this.commandList = new ArrayList<>();
    }

    public List<Command> GetCommandList(){
        return this.commandList;
    }

    public void registerCommand(Command command) {
        this.commandList.add(command);
    }

    public Optional<Command> getCommand(String name) {
        return this.commandList.stream()
                .filter( command -> command.getName().equalsIgnoreCase(name) )
                .findFirst();
    }
}