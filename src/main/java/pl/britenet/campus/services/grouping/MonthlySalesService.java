package pl.britenet.campus.services.grouping;

import pl.britenet.campus.database.DatabaseService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MonthlySalesService {
    private final DatabaseService databaseService;

    public MonthlySalesService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Map<String, Integer> getMonthlySales() {
        return this.databaseService.performSQL("SELECT MONTHNAME(order_.CreatedAt) AS MIESIAC, COUNT(orderproduct.productId) AS LICZBA\n" +
                "FROM orderproduct RIGHT JOIN order_ ON order_.Id = orderproduct.OrderId\n" +
                "GROUP BY MONTH(order_.CreatedAt);", resultSet -> {
            Map<String, Integer> monthlySales = new HashMap<>();
            try {
                while (resultSet.next()) {
                    String month = resultSet.getString("MIESIAC");
                    int sales = resultSet.getInt("LICZBA");
                    monthlySales.put(month, sales);
                }
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
            return monthlySales;
        });
    }
}