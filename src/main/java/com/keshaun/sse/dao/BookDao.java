package com.keshaun.sse.dao;

import com.keshaun.sse.model.Book;
import com.keshaun.sse.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements Dao<Book> {
    @Override
    public void add(Book book) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("INSERT INTO tbl_book(title, authId, pubId) VALUES (?, ?, ?)");
        prepared.setString(1, book.getTitle());
        prepared.setInt(2, book.getAuthId());
        prepared.setInt(3, book.getPubId());
        prepared.executeUpdate();
    }

    @Override
    public Book getSingle(int id) throws SQLException {
        Book book = null;

        PreparedStatement prepared = conn.prepareStatement("SELECT * FROM tbl_book WHERE bookId = ?");
        prepared.setInt(1, id);

        ResultSet result = prepared.executeQuery();
        if (result.next()) {
            book = new Book(result.getString("title"), result.getInt("authId"), result.getInt("pubId"));
            book.setBookId(id);
        }

        return book;
    }

    @Override
    public List<Book> getAll() throws SQLException {
        List<Book> books = new ArrayList<>();

        ResultSet result = conn.createStatement().executeQuery("SELECT * FROM tbl_book");
        while (result.next()) {
            Book book = new Book(result.getString("title"), result.getInt("authId"), result.getInt("pubId"));
            book.setBookId(result.getInt("bookId"));
        }

        return books;
    }

    @Override
    public void update(Book book) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("UPDATE tbl_book SET title = ?, authId = ?, pubId = ? WHERE bookId = ?");
        prepared.setString(1, book.getTitle());
        prepared.setInt(2, book.getAuthId());
        prepared.setInt(3, book.getPubId());
        prepared.setInt(4, book.getBookId());
        prepared.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("DELETE FROM tbl_book WHERE bookId = ?");
        prepared.setInt(1, id);
        prepared.executeUpdate();
    }

    @Override
    public void delete(Book book) throws SQLException {
        delete(book.getBookId());
    }
}
