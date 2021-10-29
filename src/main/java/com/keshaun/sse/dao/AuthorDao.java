package com.keshaun.sse.dao;

import com.keshaun.sse.model.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao implements Dao<Author> {
    @Override
    public void add(Author author) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("INSERT INTO tbl_author(authorName) VALUES (?)");
        prepared.setString(1, author.getAuthorName());
        prepared.executeUpdate();
    }

    @Override
    public Author getSingle(int id) throws SQLException {
        Author author = null;

        PreparedStatement prepared = conn.prepareStatement("SELECT * FROM tbl_author WHERE authorId = ?");
        prepared.setInt(1, id);

        ResultSet result = prepared.executeQuery();
        if (result.next()) {
            author = new Author(result.getString("authorName"));
            author.setAuthorId(id);
        }

        return author;
    }

    @Override
    public List<Author> getAll() throws SQLException {
        List<Author> authors = new ArrayList<>();

        ResultSet result = conn.createStatement().executeQuery("SELECT * FROM tbl_author");
        while (result.next()) {
            Author author = new Author(result.getString("authorName"));
            author.setAuthorId(result.getInt("authorId"));
            authors.add(author);
        }

        return authors;
    }

    @Override
    public void update(Author author) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("UPDATE tbl_author SET authorName = ? WHERE authorId = ?");
        prepared.setString(1, author.getAuthorName());
        prepared.setInt(2, author.getAuthorId());
        prepared.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("DELETE FROM tbl_author WHERE authorId = ?");
        prepared.setInt(1, id);
        prepared.executeUpdate();
    }
}
