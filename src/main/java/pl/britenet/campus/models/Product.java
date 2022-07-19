package pl.britenet.campus.models;

import pl.britenet.campus.Constants;

import java.util.Date;

public class Product {
    private final int Id;
    private String Name;
    private String Description;
    private double Price;
    private Date AddedAt;
    private int ImageId;
    private Image image;

    public Product() {
        this.Id = Constants.INVALID_ID;
    }

    public Product(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public Date getAddedAt() {
        return AddedAt;
    }

    public void setAddedAt(Date addedAt) {
        AddedAt = addedAt;
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
    public String toString(){
        return this.Id + " " + this.Name + " " + this.Description + " " + this.Price + " " + this.AddedAt.toString();
    }
}
