package com.keshaun.sse.dao;

import com.keshaun.sse.model.Book;
import com.keshaun.sse.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    Connection conn = DatabaseConnection.getInstance().getConnection();

    void add(T toAdd) throws SQLException;

    T getSingle(int id) throws SQLException;

    List<T> getAll() throws SQLException;

    void update(T toUpdate) throws SQLException;

    void delete(int id) throws SQLException;

    void delete(T toDelete) throws SQLException;
}
