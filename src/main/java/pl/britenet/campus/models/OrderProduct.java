package pl.britenet.campus.models;

import pl.britenet.campus.Constants;

import java.util.Date;

public class OrderProduct {
    private int Id;
    private int ProductId;
    private int OrderId;
    private Date CreatedAt;
    private Order order;
    private Product product;

    public OrderProduct() { }

    public OrderProduct(int id) {
        this.Id = id;
    }

    public OrderProduct(int id, int productId, int orderId, Date createdAt, Order order, Product product) {
        Id = id;
        ProductId = productId;
        OrderId = orderId;
        CreatedAt = createdAt;
        this.order = order;
        this.product = product;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return this.Id + " " + this.CreatedAt.toString() + " " + this.OrderId + " " + this.ProductId + " " + this.product.getName()
                + " " + this.product.getDescription() + " " + this.product.getPrice();
    }
}
