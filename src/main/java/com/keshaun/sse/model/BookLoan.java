package com.keshaun.sse.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BookLoan {
    private int bookId;
    private int branchId;
    private int cardNo;
    private LocalDateTime dateOut;
    private LocalDateTime dueDate;

    public BookLoan() {}

    public BookLoan(int bookId, int branchId, int cardNo, Timestamp dateOut, Timestamp dueDate) {
        this.bookId = bookId;
        this.branchId = branchId;
        this.cardNo = cardNo;
        this.dateOut = dateOut.toLocalDateTime();
        this.dueDate = dueDate.toLocalDateTime();
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public LocalDateTime getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDateTime dateOut) {
        this.dateOut = dateOut;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
