package pl.britenet.campus.services.grouping;

import pl.britenet.campus.database.DatabaseService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CartCountService {
    private final DatabaseService databaseService;

    public CartCountService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Map<Integer, Integer> getCartCount() {
        return this.databaseService.performSQL("SELECT cart.userId AS ID_USERA, COUNT(cartproduct.productId) AS LICZBA_PRODUKTOW\n" +
                "FROM cartproduct RIGHT JOIN cart ON cartproduct.CartId = cart.Id\n" +
                "GROUP BY cart.userId;", resultSet -> {
            Map<Integer, Integer> cartCount = new HashMap<>();
            try {
                while (resultSet.next()) {
                    int userID = resultSet.getInt("ID_USERA");
                    int count = resultSet.getInt("LICZBA_PRODUKTOW");
                    cartCount.put(userID, count);
                }
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
            return cartCount;
        });
    }

}
