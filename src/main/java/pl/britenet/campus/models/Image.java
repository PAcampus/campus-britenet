package pl.britenet.campus.models;

import pl.britenet.campus.Constants;

public class Image {
    private int Id;
    private String path;

    public Image() {
        this.Id = Constants.INVALID_ID;
    }

    public Image(int Id) {
        this.Id = Id;
    }

    public Image(int Id, String path) {
        this.Id = Id;
        this.path = path;
    }


    public int getId() {
        return Id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return this.Id + " " + this.path;
    }
}
