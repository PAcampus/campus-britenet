package pl.britenet.campus.models;

import pl.britenet.campus.Constants;

import java.util.Date;

public class Order {
    private int Id;
    private int CartId;
    private int UserId;
    private Date CreatedAt;

    public Order() { }

    public Order(int id) {
        this.Id = id;
    }

    public Order(int id, int cartId, int userId, Date createdAt) {
        Id = id;
        CartId = cartId;
        UserId = userId;
        CreatedAt = createdAt;
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

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }

    @Override
    public String toString() {
        return this.Id + " " + this.CartId + " " + this.UserId + " " + this.CreatedAt.toString();
    }
}
