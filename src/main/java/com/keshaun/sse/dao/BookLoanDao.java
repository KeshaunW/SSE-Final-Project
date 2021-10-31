package com.keshaun.sse.dao;

import com.keshaun.sse.model.BookLoan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookLoanDao implements Dao<BookLoan> {
    @Override
    public void add(BookLoan bookLoan) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("INSERT INTO tbl_book_loans VALUES (?, ?, ?, ?, ?)");
        prepared.setInt(1, bookLoan.getBookId());
        prepared.setInt(2, bookLoan.getBranchId());
        prepared.setInt(3, bookLoan.getCardNo());
        prepared.setTimestamp(4, Timestamp.valueOf(bookLoan.getDateOut()));
        prepared.setTimestamp(5, Timestamp.valueOf(bookLoan.getDueDate()));
        prepared.executeUpdate();
    }

    @Override
    public BookLoan getSingle(int id) throws SQLException {
        return null;
    }

    public BookLoan getSingle(int bookId, int branchId, int cardNo) throws SQLException {
        BookLoan bookLoan = null;

        PreparedStatement prepared = conn.prepareStatement("SELECT * FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ?");
        prepared.setInt(1, bookId);
        prepared.setInt(2, branchId);
        prepared.setInt(3, cardNo);

        ResultSet result = prepared.executeQuery();
        if (result.next()) {
            bookLoan = new BookLoan(bookId, branchId, cardNo, result.getTimestamp("dateOut"), result.getTimestamp("dueDate"));
        }

        return bookLoan;
    }

    public List<BookLoan> getAllBySingleFilter(int id, int filter) throws SQLException {
        List <BookLoan> bookLoans = new ArrayList<>();
        String sql = "SELECT * FROM tbl_book_loans WHERE filter = ?";

        sql = switch (filter) {
            case 1 -> sql.replace("filter", "bookId");
            case 2 -> sql.replace("filter", "branchId");
            default -> sql.replace("filter", "cardNo");
        };

        PreparedStatement prepared = conn.prepareStatement(sql);
        prepared.setInt(1, id);

        ResultSet result = prepared.executeQuery();
        while (result.next()) {
            BookLoan bookLoan = new BookLoan(result.getInt("bookId"), result.getInt("branchId"), result.getInt("cardNo"), result.getTimestamp("dateOut"), result.getTimestamp("dueDate"));
            bookLoans.add(bookLoan);
        }

        return bookLoans;
    }

    public List<BookLoan> getAllByTwoFilters(int[] ids, int[] filters) throws SQLException {
        List <BookLoan> bookLoans = new ArrayList<>();
        String sql = "SELECT * FROM tbl_book_loans WHERE filter = ? AND filter = ?";

        if (filters[0] == filters[1])
            return getAllBySingleFilter(ids[0], filters[0]);

        for (int i = 0; i < 2; i++) {
            sql = switch (filters[i]) {
                case 1 -> sql.replaceFirst("filter", "bookId");
                case 2 -> sql.replaceFirst("filter", "branchId");
                default -> sql.replaceFirst("filter", "cardNo");
            };
        }

        PreparedStatement prepared = conn.prepareStatement(sql);
        prepared.setInt(1, ids[0]);
        prepared.setInt(2, ids[1]);

        ResultSet result = prepared.executeQuery();
        while (result.next()) {
            BookLoan bookLoan = new BookLoan(result.getInt("bookId"), result.getInt("branchId"), result.getInt("cardNo"), result.getTimestamp("dateOut"), result.getTimestamp("dueDate"));
            bookLoans.add(bookLoan);
        }

        return bookLoans;
    }

    @Override
    public List<BookLoan> getAll() throws SQLException {
        List <BookLoan> bookLoans = new ArrayList<>();

        ResultSet result = conn.createStatement().executeQuery("SELECT * FROM tbl_book_loans");
        while (result.next()) {
            BookLoan bookLoan = new BookLoan(result.getInt("bookId"), result.getInt("branchId"), result.getInt("cardNo"), result.getTimestamp("dateOut"), result.getTimestamp("dueDate"));
            bookLoans.add(bookLoan);
        }

        return bookLoans;
    }

    @Override
    public void update(BookLoan bookLoan) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("UPDATE tbl_book_loans SET dateOut = ?, dueDate = ? WHERE bookId = ? AND branchId = ? and cardNo = ?");
        prepared.setTimestamp(1, Timestamp.valueOf(bookLoan.getDateOut()));
        prepared.setTimestamp(2, Timestamp.valueOf(bookLoan.getDueDate()));
        prepared.setInt(3, bookLoan.getBookId());
        prepared.setInt(4, bookLoan.getBranchId());
        prepared.setInt(5, bookLoan.getCardNo());
        prepared.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {}

    public void delete(int bookId, int branchId, int cardNo) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("DELETE FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ?");
        prepared.setInt(1, bookId);
        prepared.setInt(2, branchId);
        prepared.setInt(3, cardNo);
        prepared.executeUpdate();
    }
}
