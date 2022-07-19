package pl.britenet.campus.models;

import pl.britenet.campus.Constants;

import java.util.Date;

public class Order {
    private final int Id;
    private int CartId;
    private int UserId;
    private Date CreatedAt;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public Order() {
        this.Id = Constants.INVALID_ID;
    }

    public Order(int id) {
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
