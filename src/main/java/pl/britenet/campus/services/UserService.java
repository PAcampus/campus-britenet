package pl.britenet.campus.services;

import pl.britenet.campus.database.DatabaseService;
import pl.britenet.campus.models.User;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final DatabaseService databaseService;

    public UserService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<User> getUsers() {
        return this.databaseService.performSQL("SELECT * FROM user_", resultSet -> {
            List<User> userList = new LinkedList<>();
            try {
                while (resultSet.next()) {
                    User user = new User(resultSet.getInt("Id"));
                    user.setName(resultSet.getString("Name"));
                    user.setLast_name(resultSet.getString("Last_name"));
                    user.setAddress(resultSet.getString("Address"));
                    user.setEmail(resultSet.getString("Email"));
                    user.setPassword(resultSet.getString("Password"));
                    user.setPhone_number(resultSet.getString("Phone_number"));
                    user.setCartId(resultSet.getInt("CartId"));
                    userList.add(user);
                }
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
            return userList;
        });
    }

    public Optional<User> getUser(int id) {
        User retrievedUser = this.databaseService.performSQL(
                String.format("SELECT * FROM user_ WHERE Id = %d", id), resultSet -> {
            try {
                if(resultSet.next()) {
                    User user = new User(resultSet.getInt("Id"));
                    user.setName(resultSet.getString("Name"));
                    user.setLast_name(resultSet.getString("Last_name"));
                    user.setAddress(resultSet.getString("Address"));
                    user.setEmail(resultSet.getString("Email"));
                    user.setPassword(resultSet.getString("Password"));
                    user.setPhone_number(resultSet.getString("Phone_number"));
                    user.setCartId(resultSet.getInt("CartId"));
                    return user;
                }
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
            return null;
        });
        return Optional.of(retrievedUser);
    }

    public Optional<User> getUser(String email, String password) {
        User retrievedProduct = this.databaseService.performSQL(String.format("SELECT * FROM user WHERE username='%s' AND password='%s'", email, password), resultSet -> {
            try {
                if (resultSet.next()) {
                    User user = new User(resultSet.getInt("id"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    return user;
                }
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
            return null;
        });

        return Optional.of(retrievedProduct);
    }

    public void insertUser(User user) {
        this.databaseService.performDML(String.format(
                "INSERT INTO user_(Name, Last_name, Address, Email, Password, Phone_number ) " +
                        "VALUES('%s', '%s', '%s', '%s', '%s', '%s')",
                user.getName(), user.getLast_name(), user.getAddress(), user.getEmail(),
                user.getPassword(), user.getPhone_number()));
    }

    public void deleteUser(int id) {
        this.databaseService.performDML("SET foreign_key_checks = 0");
        this.databaseService.performDML(String.format(
                "DELETE FROM user_ WHERE Id = %d", id));
        this.databaseService.performDML("SET foreign_key_checks = 1");
    }

    public void updateUser(User user) {
        this.databaseService.performDML("SET foreign_key_checks = 0");
        this.databaseService.performDML(String.format(
                "UPDATE user_ SET Name = '%s', Last_name = '%s', Address = '%s', Email = '%s', " +
                        "Password = '%s', Phone_number = '%s', CartId = %d WHERE Id = %d",
                user.getName(), user.getLast_name(), user.getAddress(), user.getEmail(),
                user.getPassword(), user.getPhone_number(), user.getCartId(), user.getId()));
        this.databaseService.performDML("SET foreign_key_checks = 1");
    }
}
