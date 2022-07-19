package pl.britenet.campus.database;

import java.sql.ResultSet;

public interface ResultParser<T> {
    T parse(ResultSet resultSet);
}