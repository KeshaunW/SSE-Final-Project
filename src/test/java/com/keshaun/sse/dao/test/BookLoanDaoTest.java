package com.keshaun.sse.dao.test;

import com.keshaun.sse.dao.BookLoanDao;
import com.keshaun.sse.model.BookLoan;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.Assert.*;

public class BookLoanDaoTest {
    BookLoanDao dao = new BookLoanDao();
    BookLoan loan1 = new BookLoan(1, 10, 187, new Timestamp(2018 - 1900, 0, 21, 3, 33, 40, 0), new Timestamp(2018 - 1900, 9, 12, 0, 14, 50, 0));

    @Test
    public void testConstruct() {
        assertNotNull(loan1);
        assertNotNull(loan1.getDateOut());
        System.out.println(loan1.getDateOut());
    }

    @Test
    public void testRead() throws SQLException {
        assertNotNull(dao.getSingle(1, 10, 187));
        System.out.println(dao.getSingle(1, 10, 187).getDateOut());
        assertEquals(loan1.getDateOut(), dao.getSingle(1, 10, 187).getDateOut());
        assertTrue(dao.getAllBySingleFilter(397, 3).size() > 0);
        assertTrue(dao.getAllByTwoFilters(new int[]{1, 10}, new int[]{1, 2}).size() > 0);
    }
}
