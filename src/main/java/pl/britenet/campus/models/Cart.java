package pl.britenet.campus.models;

import pl.britenet.campus.Constants;

public class Cart {
    private int Id;
    private double Total;
    private int UserId;

    public Cart() { }

    public Cart(int Id) {
        this.Id = Id;
    }

    public Cart(int id, double total, int userId) {
        Id = id;
        Total = total;
        UserId = userId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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
