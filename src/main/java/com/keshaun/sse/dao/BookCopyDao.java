package com.keshaun.sse.dao;

import com.keshaun.sse.model.BookCopy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCopyDao implements Dao<BookCopy> {
    @Override
    public void add(BookCopy bookCopy) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("INSERT INTO tbl_book_copies VALUES (?, ?, ?)");
        prepared.setInt(1, bookCopy.getBookId());
        prepared.setInt(2, bookCopy.getBranchId());
        prepared.setInt(3, bookCopy.getNoOfCopies());
        prepared.executeUpdate();
    }

    @Override
    public BookCopy getSingle(int id) throws SQLException {
        return null;
    }

    public BookCopy getSingle(int bookId, int branchId) throws SQLException {
        BookCopy bookCopy = null;

        PreparedStatement prepared = conn.prepareStatement("SELECT * FROM tbl_book_copies WHERE bookId = ? AND branchId = ?");
        prepared.setInt(1, bookId);
        prepared.setInt(2, branchId);

        ResultSet result = prepared.executeQuery();
        if (result.next()) {
            bookCopy = new BookCopy(bookId, branchId, result.getInt("noOfCopies"));
        }

        return bookCopy;
    }

    public List<BookCopy> getAllBySingleFilter(int id, int filter) throws SQLException {
        List<BookCopy> bookCopies = new ArrayList<>();
        String sql = "SELECT * FROM tbl_book_copies WHERE filter = ?";

        sql = switch (filter) {
            case 1 -> sql.replace("filter", "bookId");
            default -> sql.replace("filter", "branchId");
        };

        PreparedStatement prepared = conn.prepareStatement(sql);
        prepared.setInt(1, id);

        ResultSet result = prepared.executeQuery();
        while (result.next()) {
            BookCopy bookCopy = new BookCopy(result.getInt("bookId"), result.getInt("branchId"), result.getInt("noOfCopies"));
            bookCopies.add(bookCopy);
        }

        return bookCopies;
    }

    @Override
    public List<BookCopy> getAll() throws SQLException {
        List<BookCopy> bookCopies = new ArrayList<>();

        ResultSet result = conn.createStatement().executeQuery("SELECT * FROM tbl_book_copies");
        while (result.next()) {
            BookCopy bookCopy = new BookCopy(result.getInt("bookId"), result.getInt("branchId"), result.getInt("noOfCopies"));
            bookCopies.add(bookCopy);
        }

        return bookCopies;
    }

    @Override
    public void update(BookCopy bookCopy) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId = ?");
        prepared.setInt(1, bookCopy.getNoOfCopies());
        prepared.setInt(2, bookCopy.getBookId());
        prepared.setInt(3, bookCopy.getBranchId());
        prepared.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {}

    public void delete(int book, int branch) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("DELETE FROM tbl_book_copies WHERE bookId = ? AND branchId = ?");
        prepared.setInt(1, book);
        prepared.setInt(2, branch);
        prepared.executeUpdate();
    }
}
