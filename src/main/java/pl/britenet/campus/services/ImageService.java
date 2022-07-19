package pl.britenet.campus.services;

import pl.britenet.campus.database.DatabaseService;
import pl.britenet.campus.models.Image;
import pl.britenet.campus.models.Product;
import pl.britenet.campus.models.User;

public class ImageService {
    private final DatabaseService databaseService;

    public ImageService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void addImageToUser(User user, Image image) {
        this.databaseService.performDML(String.format(
                "UPDATE user_ SET ImageId = %d WHERE Id = %d",
                image.getId(),user.getId()));
        user.setImageId(image.getId());
        user.setImage(image);
    }

    public void addImageToProduct(Product product, Image image) {
        this.databaseService.performDML(String.format(
                "UPDATE product SET ImageId = %d WHERE Id = %d",
                image.getId(),product.getId()));
        product.setImageId(image.getId());
        product.setImage(image);
    }

    public void insertImage(Image image) {
        this.databaseService.performDML(String.format("INSERT INTO image (path) VALUES ('%s')", image.getPath()));
    }

    public void deleteImage(int id) {
        this.databaseService.performDML("SET foreign_key_checks = 0");
        this.databaseService.performDML(
                String.format("DELETE FROM image WHERE Id = %d;", id));
        this.databaseService.performDML("SET foreign_key_checks = 1");
    }

    public void updateImage(Image image) {
        this.databaseService.performDML("SET foreign_key_checks = 0");
        this.databaseService.performDML(
                String.format("UPDATE product SET path='%s' WHERE Id = %d;",image.getPath(), image.getId()));
        this.databaseService.performDML("SET foreign_key_checks = 1");
    }

}
