package com.keshaun.sse.menu;

import com.keshaun.sse.dao.*;
import com.keshaun.sse.model.*;
import com.keshaun.sse.util.Input;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class BorrowerMenu {
    private final static AuthorDao authorDao = new AuthorDao();
    private final static BookDao bookDao = new BookDao();
    private final static BorrowerDao borrowerDao = new BorrowerDao();
    private final static BranchDao branchDao = new BranchDao();
    private final static BookCopyDao copyDao = new BookCopyDao();
    private final static BookLoanDao loanDao = new BookLoanDao();

    public static void mainMenu() throws SQLException {
        System.out.println("Please enter your Card Number: ");
        Borrower borrower = null;

        while (borrower == null) {
            int cardNo = Input.getCardNo();
            Borrower temp = borrowerDao.getSingle(cardNo);
            if (temp == null)
                System.out.println("The provided Card Number is not in the system. Please try again.");
            borrower = temp;
        }

        System.out.println();
        menu2(borrower);
    }

    private static void menu2(Borrower borrower) throws SQLException {
        System.out.println("Welcome, " + borrower.getName() + ". Please select an option below:\n");

        System.out.println("1) Check Out a Book");
        System.out.println("2) Return a Book");
        System.out.println("3) Quit to Previous Menu\n");

        int option = Input.getInt(1, 3);
        System.out.println();

        switch(option) {
            case 1 -> menu3(borrower, 1, false);
            case 2 -> menu3(borrower, 1, true);
            default -> Main.mainMenu();
        }
    }

    private static void menu3(Borrower borrower, int page, boolean returning) throws SQLException {
        System.out.println("Please select a library branch below:\n");

        List<Branch> branches = branchDao.getAll();

        int i = (page - 1) * 15 + 1;
        while (i <= page * 15) {
            Branch branch = branches.get(i-1);
            System.out.println(i + ") " + branch);
            i++;
        }

        System.out.println(i + ") Return to Previous Menu");
        if ((page + 1) * 10 < branches.size())
            System.out.println((i+1) + ") Next Page of Branches");
        if (page > 1)
            System.out.println((i+2) + ") Previous Page of Branches");
        System.out.println();

        int option = Input.getInt((page - 1) * 15 + 1, page * 15 + 3);
        System.out.println();

        if (option == i) {
            menu2(borrower);
        } else if (option == i+1) {
            if ((page + 1) * 15 < branches.size()) {
                menu3(borrower,page + 1, returning);
            } else {
                System.out.println("Please enter a valid input.");
                menu3(borrower, page, returning);
            }
        } else if (option == i+2) {
            if (page > 1) {
                menu3(borrower,page - 1, returning);
            } else {
                System.out.println("Please enter a valid input.");
                menu3(borrower, page, returning);
            }
        } else {
            if (returning)
                menu5(borrower, branches.get(option-1));
            else
                menu4(borrower, branches.get(option-1));
        }
    }

    private static void menu4(Borrower borrower, Branch branch) throws SQLException {
        System.out.println("Select the book you would like to borrow:\n");

        List<BookCopy> copies = copyDao.getAllBySingleFilter(branch.getBranchId(), 2);

        int i = 1;
        for (BookCopy copy : copies) {
            Book book = bookDao.getSingle(copy.getBookId());
            Author author = authorDao.getSingle(book.getAuthId());
            System.out.println(i + ") " + book.getTitle() + " by " + author.getAuthorName());
            i++;
        }
        System.out.println(i + ") Return to Previous Menu");

        int option = Input.getInt(1, i);
        System.out.println();

        if (option == i) {
            System.out.println("Transaction Cancelled.\n");
        } else {
            BookCopy copy = copies.get(option);
            copy.setNoOfCopies(copy.getNoOfCopies() - 1);
            LocalDateTime dateOut = LocalDateTime.now();
            LocalDateTime dueDate = dateOut.plusDays(7);
            BookLoan loan = new BookLoan(copy.getBookId(), branch.getBranchId(), borrower.getCardNo(), dateOut, dueDate);
            loanDao.add(loan);
            copyDao.update(copy);
        }
        menu2(borrower);
    }

    private static void menu5(Borrower borrower, Branch branch) throws SQLException {
        List<BookLoan> loans = loanDao.getAllByTwoFilters(new int[]{branch.getBranchId(), borrower.getCardNo()}, new int[]{2, 3});

        if (loans.size() == 0) {
            System.out.println("You do not have any books to return to this library.\n");
        } else {
            System.out.println("Select the book you would like to return:\n");

            int i = 1;
            for (BookLoan loan : loans) {
                Book book = bookDao.getSingle(loan.getBookId());
                Author author = authorDao.getSingle(book.getAuthId());
                System.out.println(i + ") " + book.getTitle() + " by " + author.getAuthorName());
                i++;
            }
            System.out.println(i + ") Return to Previous Menu");

            int option = Input.getInt(1, i);
            System.out.println();

            if (option == i) {
                System.out.println("Transaction Cancelled.\n");
            } else {
                BookLoan loan = loans.get(option);
                BookCopy copy = copyDao.getSingle(loan.getBookId(), loan.getBranchId());
                copy.setNoOfCopies(copy.getNoOfCopies() + 1);
                copyDao.update(copy);
                loanDao.delete(loan);
            }
        }
        menu2(borrower);
    }
}
