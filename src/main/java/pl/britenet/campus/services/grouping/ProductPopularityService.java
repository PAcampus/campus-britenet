package pl.britenet.campus.services.grouping;

import pl.britenet.campus.database.DatabaseService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProductPopularityService {
    private final DatabaseService databaseService;

    public ProductPopularityService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Map<Integer, Integer> getPopularityProducts() {
        return this.databaseService.performSQL("SELECT p.id AS ID, COUNT(op.ProductId) AS ITEMS_BOUGHT\n" +
                "FROM product AS p LEFT JOIN orderproduct AS op ON p.Id = op.ProductId\n" +
                "GROUP BY p.Id\n" +
                "ORDER BY ITEMS_BOUGHT DESC;", resultSet -> {
            Map<Integer, Integer> popularProducts = new HashMap<>();
            try {
                while (resultSet.next()) {
                    int productId = resultSet.getInt("ID");
                    int items_bought = resultSet.getInt("ITEMS_BOUGHT");
                    popularProducts.put(productId, items_bought);
                }
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
            return popularProducts;
        });
    }
}
