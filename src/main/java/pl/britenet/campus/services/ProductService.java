package pl.britenet.campus.services;

import pl.britenet.campus.database.DatabaseService;
import pl.britenet.campus.models.Product;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {

    private final DatabaseService databaseService;

    public ProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<Product> getProducts() {
        return this.databaseService.performSQL("SELECT * FROM product", resultSet -> {
            List<Product> productList = new ArrayList<>();
            try {
                while (resultSet.next()) {
                    Product product = new Product(resultSet.getInt("Id"));
                    product.setName(resultSet.getString("Name"));
                    product.setDescription(resultSet.getString("Description"));
                    product.setPrice(resultSet.getDouble("Price"));
                    product.setAddedAt(resultSet.getDate("AddedAt"));
                    productList.add(product);
                }
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
            return productList;
        });
    }

    public Optional<Product> getProduct(int id) {
        Product retrievedProduct = this.databaseService.performSQL(
                String.format("SELECT * FROM product WHERE Id = %d", id), resultSet -> {
            try {
                if (resultSet.next()) {
                    Product product = new Product(resultSet.getInt("Id"));
                    product.setName(resultSet.getString("Name"));
                    product.setDescription(resultSet.getString("Description"));
                    product.setPrice(resultSet.getDouble("Price"));
                    return product;
                }
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
            return null;
        });

        return Optional.of(retrievedProduct);
    }

    public void insertProduct(Product product) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.databaseService.performDML(
                String.format("INSERT INTO product (name, description, price, addedat) VALUES ('%s', '%s', " + product.getPrice() +", '%s');",
                        product.getName(), product.getDescription(), df.format(product.getAddedAt()))
        );
    }

    public void deleteProduct(int id) {
        this.databaseService.performDML("SET foreign_key_checks = 0");
        this.databaseService.performDML(
                String.format("DELETE FROM product WHERE Id = %d;", id));
        this.databaseService.performDML("SET foreign_key_checks = 1");
    }

    public void updateProduct(Product product) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.databaseService.performDML(String.format(
                "UPDATE product SET Name = '%s', Description = '%s', Price = " + product.getPrice() +", AddedAt = '%s' WHERE Id = %d",
                product.getName(), product.getDescription(), df.format(product.getAddedAt()), product.getId()));
    }
}
