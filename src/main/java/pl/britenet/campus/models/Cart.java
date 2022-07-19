package pl.britenet.campus.models;

import pl.britenet.campus.Constants;

public class Cart {
    private final int Id;
    private double Total;
    private int UserId;

    public Cart() {
        this.Id = Constants.INVALID_ID;
    }

    public Cart(int Id) {
        this.Id = Id;
    }

    public int getId() {
        return Id;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    @Override
    public String toString() {
        return this.Id + " " + this.UserId + " " + this.Total;
    }
}
