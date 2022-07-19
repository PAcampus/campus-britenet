package pl.britenet.campus.services;

import pl.britenet.campus.database.DatabaseService;
import pl.britenet.campus.models.Order;
import pl.britenet.campus.models.OrderProduct;
import pl.britenet.campus.models.Product;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OrderProductService {
    private final DatabaseService databaseService;

    public OrderProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<OrderProduct> getOrderProducts() {
        return this.databaseService.performSQL(
                "SELECT o.Id AS oId, o.CartId AS oCartId, o.UserId AS oUserId, o.CreatedAt AS oCreatedAt,\n" +
                        "       op.Id AS opId, op.ProductId AS opProductId, op.CreatedAt AS opCreatedAt,\n" +
                        "       p.name AS Name, p.description AS description, p.price AS Price\n" +
                        "FROM\n" +
                        "    order_ AS o\n" +
                        "         INNER JOIN\n" +
                        "    orderproduct AS op ON o.Id = op.OrderId\n" +
                        "         INNER JOIN\n" +
                        "    product AS p ON op.ProductId = p.Id;", resultSet -> {
            List<OrderProduct> orderProductList = new LinkedList<>();
            try {
                while (resultSet.next()) {
                    Order order = new Order(resultSet.getInt("oId"));
                    order.setUserId(resultSet.getInt("oUserId"));
                    order.setCartId(resultSet.getInt("oCartId"));
                    order.setCreatedAt(resultSet.getDate("oCreatedAt"));
                    Product product = new Product(resultSet.getInt("opProductId"));
                    product.setName(resultSet.getString("Name"));
                    product.setDescription(resultSet.getString("Description"));
                    product.setPrice(resultSet.getDouble("Price"));
                    OrderProduct orderProduct = new OrderProduct(resultSet.getInt("opId"));
                    orderProduct.setOrderId(resultSet.getInt("oId"));
                    orderProduct.setProductId(resultSet.getInt("opProductId"));
                    orderProduct.setProduct(product);
                    orderProduct.setOrder(order);
                    orderProduct.setCreatedAt(resultSet.getDate("opCreatedAt"));
                    orderProductList.add(orderProduct);
                }
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
            return orderProductList;
        });
    }

    public Optional<OrderProduct> getOrderProduct(int id) {
        OrderProduct retrievedOrderProduct = this.databaseService.performSQL(
                String.format("SELECT o.Id AS oId, o.CartId AS oCartId, o.UserId AS oUserId, o.CreatedAt AS oCreatedAt,\n" +
                        "                               op.Id AS opId, op.ProductId AS opProductId, op.CreatedAt AS opCreatedAt,\n" +
                        "                               p.name AS Name, p.description AS description, p.price AS Price\n" +
                        "                        FROM\n" +
                        "                            order_ AS o\n" +
                        "                                 INNER JOIN\n" +
                        "                            orderproduct AS op ON o.Id = op.OrderId\n" +
                        "                                 INNER JOIN\n" +
                        "                            product AS p ON op.ProductId = p.Id\n" +
                        "                        WHERE op.Id = %d;", id), resultSet -> {
            try {
                if (resultSet.next()) {
                    Order order = new Order(resultSet.getInt("oId"));
                    order.setUserId(resultSet.getInt("oUserId"));
                    order.setCartId(resultSet.getInt("oCartId"));
                    order.setCreatedAt(resultSet.getDate("oCreatedAt"));
                    Product product = new Product(resultSet.getInt("opProductId"));
                    product.setName(resultSet.getString("Name"));
                    product.setDescription(resultSet.getString("Description"));
                    product.setPrice(resultSet.getDouble("Price"));
                    OrderProduct orderProduct = new OrderProduct(resultSet.getInt("opId"));
                    orderProduct.setOrderId(resultSet.getInt("oId"));
                    orderProduct.setProductId(resultSet.getInt("opProductId"));
                    orderProduct.setProduct(product);
                    orderProduct.setOrder(order);
                    orderProduct.setCreatedAt(resultSet.getDate("opCreatedAt"));
                    return orderProduct;
                }
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
            return null;
        });
        return Optional.of(retrievedOrderProduct);
    }

    public void insertOrderProduct(OrderProduct orderProduct) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.databaseService.performDML(String.format(
                "INSERT INTO orderproduct(OrderId, ProductId, CreatedAt) VALUES(%d, %d, '%s');",
                orderProduct.getOrderId(), orderProduct.getProductId(), df.format(orderProduct.getCreatedAt())));
    }

    public void deleteOrderProduct(int id) {
        this.databaseService.performDML("SET foreign_key_checks = 0");
        this.databaseService.performDML(String.format(
                "DELETE FROM orderproduct WHERE Id = %d;", id));
        this.databaseService.performDML("SET foreign_key_checks = 1");
    }

    public void updateOrderProduct(OrderProduct orderProduct) {
        this.databaseService.performDML("SET foreign_key_checks = 0");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.databaseService.performDML(String.format(
                "UPDATE orderproduct SET OrderId = %d, ProductId = %d, CreatedAt = '%s' WHERE Id = %d;",
                orderProduct.getOrderId(), orderProduct.getProductId(), df.format(orderProduct.getCreatedAt()), orderProduct.getId()));
        this.databaseService.performDML("SET foreign_key_checks = 1");
    }
}
