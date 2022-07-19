package pl.britenet.campus.services;

import pl.britenet.campus.database.DatabaseService;
import pl.britenet.campus.models.Cart;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CartService {
    private final DatabaseService databaseService;

    public CartService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<Cart> getCarts() {
        return this.databaseService.performSQL("SELECT * FROM cart", resultSet -> {
            List<Cart> cartList = new LinkedList<>();
            try {
                while (resultSet.next()) {
                    Cart cart = new Cart(resultSet.getInt("Id"));
                    cart.setUserId(resultSet.getInt("UserId"));
                    cart.setTotal(resultSet.getDouble("Total"));
                    cartList.add(cart);
                }
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
            return cartList;
        });
    }

    public Optional<Cart> getCart(int id) {
        Cart retrievedCart = this.databaseService.performSQL(
                String.format("SELECT * FROM cart WHERE Id = %d", id), resultSet -> {
           try {
               if(resultSet.next()) {
                   Cart cart = new Cart(resultSet.getInt("Id"));
                   cart.setUserId(resultSet.getInt("UserId"));
                   cart.setTotal(resultSet.getDouble("Total"));
                   return cart;
               }
           } catch (SQLException exception) {
               throw new IllegalStateException(exception);
           }
           return null;
        });
        return Optional.of(retrievedCart);
    }

    public void insertCart(Cart cart) {
        this.databaseService.performDML(String.format(
                "INSERT INTO cart(UserId, Total) VALUES(%d," + cart.getTotal() + ")",
                cart.getUserId()));
    }

    public void deleteCart(int id) {
        this.databaseService.performDML("SET foreign_key_checks = 0");
        this.databaseService.performDML(String.format(
                "DELETE FROM cart WHERE Id = %d",id));
        this.databaseService.performDML("SET foreign_key_checks = 1");
    }

    public void updateCart(Cart cart) {
        this.databaseService.performDML(String.format(
                "UPDATE cart SET UserId = %d, Total = " + cart.getTotal() +" WHERE Id = %d",
                cart.getUserId(), cart.getId()));
    }
}
