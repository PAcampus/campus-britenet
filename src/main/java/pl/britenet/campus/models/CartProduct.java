package pl.britenet.campus.models;

import pl.britenet.campus.Constants;

public class CartProduct {
    private final int Id;
    private int CartId;
    private int ProductId;
    private Cart cart;
    private Product product;

    public CartProduct() {
        this.Id = Constants.INVALID_ID;
    }

    public CartProduct(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }

    public int getCartId() {
        return CartId;
    }

    public void setCartId(int cartId) {
        CartId = cartId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return this.Id + " " + this.CartId + " " + this.cart.getTotal() + " " + this.product.getName()
                + " " + this.product.getDescription() + " " + this.product.getPrice();
    }
}
