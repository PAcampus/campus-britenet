package pl.britenet.campus.models;

import pl.britenet.campus.Constants;

public class CartProduct {
    private int Id;
    private int CartId;
    private int ProductId;
    private Cart cart;
    private Product product;

    public CartProduct() { }

    public CartProduct(int id) {
        this.Id = id;
    }

    public CartProduct(int id, int cartId, int productId, Cart cart, Product product) {
        Id = id;
        CartId = cartId;
        ProductId = productId;
        this.cart = cart;
        this.product = product;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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
