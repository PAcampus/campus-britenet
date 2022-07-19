package pl.britenet.campus.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseService {

    private HikariDataSource hikariDataSource;

    private void configure() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/campus");
        config.setUsername("root");
        config.setPassword("");
        this.hikariDataSource = new HikariDataSource(config);
    }

    public DatabaseService() {
        this.configure();
    }

    public void performDML(String dml) {
        try (Connection connection = this.hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(dml)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public<T> T performSQL(String sql, ResultParser<T> resultParser) {
        try (Connection connection = this.hikariDataSource.getConnection() ;
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            return resultParser.parse(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
}
