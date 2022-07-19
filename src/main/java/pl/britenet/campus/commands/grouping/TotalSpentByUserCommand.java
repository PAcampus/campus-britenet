package pl.britenet.campus.commands.grouping;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.services.grouping.TotalSpentByUserService;

import java.text.ParseException;
import java.util.Map;

public class TotalSpentByUserCommand extends Command {
    private final TotalSpentByUserService totalSpentByUserService;

    public TotalSpentByUserCommand(TotalSpentByUserService totalSpentByUserService) {
        super(Constants.COMMAND_TOTAL_SPENT);
        this.totalSpentByUserService = totalSpentByUserService;
    }

    @Override
    public void execute() throws ParseException {
        Map<Integer, Double> totalSpent = this.totalSpentByUserService.getTotalSpent();
        for (Map.Entry<Integer, Double> entry: totalSpent.entrySet()) {
            System.out.println("UserId: " + entry.getKey() + " Total Spent: " + entry.getValue());
        }
    }
}
