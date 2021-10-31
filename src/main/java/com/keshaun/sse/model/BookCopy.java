package com.keshaun.sse.model;

public class BookCopy {
    private int bookId;
    private int branchId;
    private int noOfCopies;

    public BookCopy() {}

    public BookCopy(int bookId, int branchId, int noOfCopies) {
        this.bookId = bookId;
        this.branchId = branchId;
        this.noOfCopies = noOfCopies;
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

    public int getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }
}
