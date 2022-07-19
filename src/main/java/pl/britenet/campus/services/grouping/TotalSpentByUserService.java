package pl.britenet.campus.services.grouping;

import pl.britenet.campus.database.DatabaseService;
import pl.britenet.campus.models.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TotalSpentByUserService {
    private final DatabaseService databaseService;

    public TotalSpentByUserService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Map<Integer, Double> getTotalSpent() {
        return this.databaseService.performSQL(
                "SELECT us.Id AS ID, us.name AS NAME, us.Last_name AS LAST_NAME, (SELECT SUM(p.Price)\n" +
                "                                        FROM \n" +
                "                                        order_ AS o \n" +
                "                                            RIGHT JOIN \n" +
                "                                        orderproduct AS op ON o.Id = op.OrderId \n" +
                "                                            RIGHT JOIN\n" +
                "                                        product AS p ON op.ProductId = p.Id\n" +
                "                                        WHERE o.UserId = us.Id) AS SUMA_ZAMÓWIEŃ\n" +
                "FROM user_ AS us;",
                resultSet -> {
                    Map<Integer, Double> totalSpent = new HashMap<>();
            try {
                while (resultSet.next()) {
                    double total = resultSet.getDouble("SUMA_ZAMÓWIEŃ");
                    int userId = resultSet.getInt("ID");
                    totalSpent.put(userId, total);
                }
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
            return totalSpent;
        });
    }

}
