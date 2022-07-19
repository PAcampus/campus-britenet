package pl.britenet.campus.services;

import pl.britenet.campus.database.DatabaseService;
import pl.britenet.campus.models.Order;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private final DatabaseService databaseService;

    public OrderService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<Order> getOrders() {
        return this.databaseService.performSQL("SELECT * FROM order_", resultSet -> {
            List<Order> orderList = new LinkedList<>();
            try {
                while(resultSet.next()) {
                    Order order = new Order(resultSet.getInt("Id"));
                    order.setCartId(resultSet.getInt("CartId"));
                    order.setUserId(resultSet.getInt("UserId"));
                    order.setCreatedAt(resultSet.getDate("CreatedAt"));
                    orderList.add(order);
                }
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
            return orderList;
        });
    }

    public Optional<Order> getOrder(int id) {
        Order retrievedOrder = this.databaseService.performSQL(
                String.format("SELECT * FROM order_ WHERE Id = %d", id), resultSet -> {
            try {
                if(resultSet.next()) {
                    Order order = new Order(resultSet.getInt("Id"));
                    order.setCartId(resultSet.getInt("CartId"));
                    order.setUserId(resultSet.getInt("UserId"));
                    order.setCreatedAt(resultSet.getDate("CreatedAt"));
                    return order;
                }
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
            return null;
        });
        return Optional.of(retrievedOrder);
    }

    public void insertOrder(Order order) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.databaseService.performDML(String.format(
                "INSERT INTO order_(CartId, UserId, CreatedAt) VALUES(%d, %d, '%s')",
                order.getCartId(), order.getUserId(), df.format(order.getCreatedAt())));
    }

    public void deleteOrder(int id) {
        this.databaseService.performDML("SET foreign_key_checks = 0");
        this.databaseService.performDML(String.format(
                "DELETE FROM order_ WHERE Id = %d", id));
        this.databaseService.performDML("SET foreign_key_checks = 1");
    }

    public void updateOrder(Order order) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.databaseService.performDML(String.format(
                "UPDATE order_ SET CartId = %d, UserId = %d, CreatedAt = '%s' WHERE Id = %d",
                order.getCartId(), order.getUserId(), df.format(order.getCreatedAt()), order.getId()));
    }
}
