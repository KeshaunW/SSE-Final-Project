package com.keshaun.sse.dao;

import com.keshaun.sse.model.Author;
import com.keshaun.sse.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AuthorDao {
    private static Connection conn = DatabaseConnection.getInstance().getConnection();

    public void add(Author author) throws SQLException {}

    //public Author getAuthor(int id) throws SQLException {}

    //public List<Author> getAuthors() throws SQLException {}

    public void update(Author author) throws SQLException {}

    public void delete(int id) throws SQLException {}
}
