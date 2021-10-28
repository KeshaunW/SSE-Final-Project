package com.keshaun.sse.dao;

import com.keshaun.sse.model.Book;
import com.keshaun.sse.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookDao {
    private static Connection conn = DatabaseConnection.getInstance().getConnection();

    public void add(Book book) throws SQLException {}

    //public Book getBook(int id) throws SQLException {}

    //public List<Book> getBooks() throws SQLException {}

    public void update(Book book) throws SQLException {}

    public void delete(int id) throws SQLException {}
}
