package pl.britenet.campus.commands;

import java.text.ParseException;

public abstract class Command {
    private final String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract void execute() throws ParseException;

//    public void executeWithArgs(String[] args);

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}