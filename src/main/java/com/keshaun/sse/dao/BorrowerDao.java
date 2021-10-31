package com.keshaun.sse.dao;

import com.keshaun.sse.model.Borrower;
import com.keshaun.sse.model.Branch;
import com.keshaun.sse.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowerDao implements Dao<Borrower> {
    @Override
    public void add(Borrower borrower) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("INSERT INTO tbl_borrower(name, address, phone) VALUES (?, ?, ?)");
        prepared.setString(1, borrower.getName());
        prepared.setString(2, borrower.getAddress());
        prepared.setString(3, borrower.getPhone());
        prepared.executeUpdate();
    }

    @Override
    public Borrower getSingle(int id) throws SQLException {
        Borrower borrower = null;

        PreparedStatement prepared = conn.prepareStatement("SELECT * FROM tbl_borrower WHERE cardNo = ?");
        prepared.setInt(1, id);

        ResultSet result = prepared.executeQuery();
        if (result.next()) {
            borrower = new Borrower(result.getString("name"), result.getString("address"), result.getString("phone"));
            borrower.setCardNo(id);
        }

        return borrower;
    }

    @Override
    public List<Borrower> getAll() throws SQLException {
        List<Borrower> borrowers = new ArrayList<>();

        ResultSet result = conn.createStatement().executeQuery("SELECT * FROM tbl_borrower");
        while (result.next()) {
            Borrower borrower = new Borrower(result.getString("name"), result.getString("address"), result.getString("phone"));
            borrower.setCardNo(result.getInt("cardNo"));
            borrowers.add(borrower);
        }

        return borrowers;
    }

    @Override
    public void update(Borrower borrower) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("UPDATE tbl_borrower SET name = ?, address = ?, phone = ? WHERE cardNo = ?");
        prepared.setString(1, borrower.getName());
        prepared.setString(2, borrower.getAddress());
        prepared.setString(3, borrower.getPhone());
        prepared.setInt(4, borrower.getCardNo());
        prepared.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("DELETE FROM tbl_borrower WHERE cardNo = ?");
        prepared.setInt(1, id);
        prepared.executeUpdate();
    }

    @Override
    public void delete(Borrower borrower) throws SQLException {
        delete(borrower.getCardNo());
    }
}
