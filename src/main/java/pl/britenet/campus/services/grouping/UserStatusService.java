package pl.britenet.campus.services.grouping;

import pl.britenet.campus.database.DatabaseService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserStatusService  {
    private final DatabaseService databaseService;

    public UserStatusService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Map<Integer, String> getUserStatus() {
        return this.databaseService.performSQL(
                "SELECT us.Id AS USERID, IF(COUNT(op.ProductId) > 5, \"PRIORYTETOWY\",\"-\") AS STATUS_KLIENTA\n" +
                        "FROM user_ AS us \n" +
                        "\t\tRIGHT JOIN \n" +
                        "    order_ AS o ON us.Id = o.UserId\n" +
                        "    \tLEFT JOIN\n" +
                        "    orderproduct AS op ON o.Id = op.OrderId\n" +
                        "    GROUP BY us.Id;",
                resultSet -> {
                    Map<Integer, String> userStatus = new HashMap<>();
                    try {
                        while (resultSet.next()) {
                            int userId = resultSet.getInt("USERID");
                            String status = resultSet.getString("STATUS_KLIENTA");
                            userStatus.put(userId,status);
                        }
                    } catch (SQLException exception) {
                        throw new IllegalStateException(exception);
                    }
                    return userStatus;
                });
    }

}
