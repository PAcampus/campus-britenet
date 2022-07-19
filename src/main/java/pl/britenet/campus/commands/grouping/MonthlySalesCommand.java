package pl.britenet.campus.commands.grouping;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.services.grouping.MonthlySalesService;

import java.text.ParseException;
import java.util.Map;

public class MonthlySalesCommand extends Command {
    private final MonthlySalesService monthlySalesService;

    public MonthlySalesCommand(MonthlySalesService monthlySalesService) {
        super(Constants.COMMAND_MONTHLY_SALES);
        this.monthlySalesService = monthlySalesService;
    }

    @Override
    public void execute() throws ParseException {
        Map<String, Integer> monthlySales = this.monthlySalesService.getMonthlySales();
        for (Map.Entry<String, Integer> entry: monthlySales.entrySet()) {
            System.out.println(entry.getKey() + " SALES: " + entry.getValue());
        }
    }
}
