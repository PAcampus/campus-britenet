package pl.britenet.campus.services;

import pl.britenet.campus.database.DatabaseService;
import pl.britenet.campus.models.Cart;
import pl.britenet.campus.models.CartProduct;
import pl.britenet.campus.models.Product;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CartProductService {
    private final DatabaseService databaseService;

    public CartProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<CartProduct> getCartProducts() {
        return this.databaseService.performSQL(
                "SELECT cp.Id AS cpId, p.Id AS pId, p.name AS Name,\n" +
                        "\t\tp.description AS description, p.price AS Price,\n" +
                        "        c.Id AS cId, c.UserId AS cUserId, c.Total AS TOTAL\n" +
                        "FROM \n" +
                        "\tcart AS c \n" +
                        "\t\tINNER JOIN \n" +
                        "\tcartproduct AS cp ON cp.cartId = c.Id\n" +
                        "    \tINNER JOIN \n" +
                        "    product AS p ON cp.productId = p.Id;", resultSet -> {
            List<CartProduct> cartProductList = new LinkedList<>();
            try {
                while (resultSet.next()) {
                    Product product = new Product(resultSet.getInt("pId"));
                    product.setName(resultSet.getString("Name"));
                    product.setDescription(resultSet.getString("Description"));
                    product.setPrice(resultSet.getDouble("Price"));
                    Cart cart = new Cart(resultSet.getInt("cId"));
                    cart.setUserId(resultSet.getInt("cUserId"));
                    cart.setTotal(resultSet.getDouble("TOTAL"));
                    CartProduct cartProduct = new CartProduct(resultSet.getInt("cpId"));
                    cartProduct.setCartId(resultSet.getInt("cId"));
                    cartProduct.setProductId(resultSet.getInt("pId"));
                    cartProduct.setCart(cart);
                    cartProduct.setProduct(product);
                    cartProductList.add(cartProduct);
                }
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
            return cartProductList;
        });
    }

    public Optional<CartProduct> getCartProduct(int id) {
        CartProduct retrievedCartProduct = this.databaseService.performSQL(
                String.format("SELECT * FROM cartproduct WHERE Id = %d", id), resultSet -> {
            try {
                if(resultSet.next()) {
                    CartProduct cartProduct = new CartProduct(resultSet.getInt("Id"));
                    cartProduct.setCartId(resultSet.getInt("CartId"));
                    cartProduct.setProductId(resultSet.getInt("ProductId"));
                    return cartProduct;
                }
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
            return null;
        });
        return Optional.of(retrievedCartProduct);
    }

    public void insertCartProduct(CartProduct cartProduct) {
        this.databaseService.performDML(String.format(
                "INSERT INTO cartproduct(ProductId, CartId) VALUES(%d, %d)",
                cartProduct.getProductId(), cartProduct.getCartId()));
    }

    public void deleteCartProduct(int id) {
        this.databaseService.performDML("SET foreign_key_checks = 0");
        this.databaseService.performDML(String.format(
                "DELETE FROM cartproduct WHERE Id = %d", id));
        this.databaseService.performDML("SET foreign_key_checks = 1");
    }

    public void updateCartProduct(CartProduct cartProduct) {
        this.databaseService.performDML("SET foreign_key_checks = 0");
        this.databaseService.performDML(String.format(
                "UPDATE cartproduct SET ProductId = %d, CartId = %d WHERE Id = %d",
                cartProduct.getProductId(), cartProduct.getCartId(), cartProduct.getId()));
        this.databaseService.performDML("SET foreign_key_checks = 1");
    }
}
