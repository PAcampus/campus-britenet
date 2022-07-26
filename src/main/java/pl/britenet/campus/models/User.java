package pl.britenet.campus.models;

import pl.britenet.campus.Constants;

public class User {
    private int Id;
    private String Name;
    private String Last_name;
    private String Address;
    private String Email;
    private String Password;
    private String Phone_number;
    private int CartId;
    private int ImageId;
    private Image image;

    public User() { }

    public User(int userId) {
        this.Id = userId;
    }

    public User(int id, String name, String last_name, String address, String email,
                String password, String phone_number, int cartId, int imageId, Image image) {
        Id = id;
        Name = name;
        Last_name = last_name;
        Address = address;
        Email = email;
        Password = password;
        Phone_number = phone_number;
        CartId = cartId;
        ImageId = imageId;
        this.image = image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String phone_number) {
        Phone_number = phone_number;
    }

    public int getCartId() {
        return CartId;
    }

    public void setCartId(int cartId) {
        CartId = cartId;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return this.Id + " " + this.Name + " " + this.Last_name;
    }
}
