package com.keshaun.sse.dao;

import com.keshaun.sse.model.Borrower;
import com.keshaun.sse.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BorrowerDao {
    private static Connection conn = DatabaseConnection.getInstance().getConnection();

    public void add(Borrower borrower) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("INSERT INTO tbl_borrower(name, address, phone) VALUES (?, ?, ?)");
        prepared.setString(1, borrower.getName());
        prepared.setString(2, borrower.getAddress());
        prepared.setString(3, borrower.getPhone());
        prepared.executeUpdate();
    }

    //public Borrower getBorrower(int id) throws SQLException {}

    //public List<Borrower> getBorrowers() throws SQLException {}

    public void update(Borrower borrower) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("UPDATE tbl_borrower SET name = ?, address = ?, phone = ? WHERE cardNo = ?");
        prepared.setString(1, borrower.getName());
        prepared.setString(2, borrower.getAddress());
        prepared.setString(3, borrower.getPhone());
        prepared.setInt(4, borrower.getCardNo());
        prepared.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("DELETE FROM tbl_borrower WHERE cardNo = ?");
        prepared.setInt(1, id);
        prepared.executeUpdate();
    }
}
