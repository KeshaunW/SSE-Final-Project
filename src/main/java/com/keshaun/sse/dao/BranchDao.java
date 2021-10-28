package com.keshaun.sse.dao;

import com.keshaun.sse.model.Branch;
import com.keshaun.sse.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BranchDao {
    private static Connection conn = DatabaseConnection.getInstance().getConnection();

    public void add(Branch branch) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("INSERT INTO tbl_library_branch(branchName, branchAddress) VALUES (?, ?)");
        prepared.setString(1, branch.getBranchName());
        prepared.setString(2, branch.getBranchAddress());
        prepared.executeUpdate();
    }

    public Branch getBranch(int id) throws SQLException {
        Branch branch = null;

        PreparedStatement prepared = conn.prepareStatement("SELECT * FROM tbl_library_branch WHERE branchId = ?");
        prepared.setInt(1, id);

        ResultSet result = prepared.executeQuery();
        if (result.next()) {
            branch = new Branch(result.getString("branchName"), result.getString("branchAddress"));
        }

        return branch;
    }

    public List<Branch> getBranches() throws SQLException {
        List<Branch> branches = new ArrayList<>();

        ResultSet result = conn.createStatement().executeQuery("SELECT * FROM tbl_library_branch");
        while (result.next()) {
            Branch branch = new Branch(result.getString("branchName"), result.getString("branchAddress"));
            branches.add(branch);
        }

        return branches;
    }

    public void update(Branch branch) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?");
        prepared.setString(1, branch.getBranchName());
        prepared.setString(2, branch.getBranchAddress());
        prepared.setInt(3, branch.getBranchId());
        prepared.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        PreparedStatement prepared = conn.prepareStatement("DELETE FROM tbl_library_branch WHERE branchId = ?");
        prepared.setInt(1, id);
        prepared.executeUpdate();
    }
}
