package com.keshaun.sse.dao.test;

import com.keshaun.sse.dao.BranchDao;
import com.keshaun.sse.model.Branch;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class BranchDaoTest {
    BranchDao dao = new BranchDao();
    Branch branch1 = new Branch("Sharpstown", "03169 Anhalt Circle");

    @Test
    public void testRead() throws SQLException {
        assertNull(dao.getBranch(51));
        assertEquals(dao.getBranch(1).toString(), branch1.toString());
        assertEquals(dao.getBranches().size(), 50);
    }
}