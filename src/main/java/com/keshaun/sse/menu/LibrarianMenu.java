package com.keshaun.sse.menu;

import com.keshaun.sse.dao.AuthorDao;
import com.keshaun.sse.dao.BookCopyDao;
import com.keshaun.sse.dao.BookDao;
import com.keshaun.sse.dao.BranchDao;
import com.keshaun.sse.model.Author;
import com.keshaun.sse.model.Book;
import com.keshaun.sse.model.BookCopy;
import com.keshaun.sse.model.Branch;
import com.keshaun.sse.util.Input;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LibrarianMenu {
    private final static AuthorDao authorDao = new AuthorDao();
    private final static BookDao bookDao = new BookDao();
    private final static BranchDao branchDao = new BranchDao();
    private final static BookCopyDao copyDao = new BookCopyDao();

    public static void mainMenu() throws SQLException {
        System.out.println("Welcome, Librarian. Please select an option:\n");

        System.out.println("1) Enter Your Branch");
        System.out.println("2) Return to Previous Menu\n");

        int option = Input.getInt(1, 2);
        System.out.println();

        switch(option) {
            case 1 -> menu2(1);
            default -> Main.mainMenu();
        }
    }

    private static void menu2(int page) throws SQLException {
        List<Branch> branches = branchDao.getAll();

        int i = (page - 1) * 10 + 1;
        while (i <= page * 10) {
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

        int option = Input.getInt((page - 1) * 10 + 1, page * 10 + 3);
        System.out.println();

        if (option == i) {
            mainMenu();
        } else if (option == i+1) {
            if ((page + 1) * 10 < branches.size()) {
                menu2(page + 1);
            } else {
                System.out.println("Please enter a valid input.");
                menu2(page);
            }
        } else if (option == i+2) {
            if (page > 1) {
                menu2(page - 1);
            } else {
                System.out.println("Please enter a valid input.");
                menu2(page);
            }
        } else {
            menu3(branches.get(option-1));
        }
    }

    private static void menu3(Branch branch) throws SQLException {
        System.out.println("Please select an option:\n");

        System.out.println("1) Update the Details for the " + branch.getBranchName() + " Branch");
        System.out.println("2) Add Copies of Books to the " + branch.getBranchName() + " Branch");
        System.out.println("3) Return to Previous Menu\n");

        int option = Input.getInt(1, 2);
        System.out.println();

        switch(option) {
            case 1 -> menu4(branch);
            case 2 -> menu5(branch, 1);
            default -> menu2(1);
        }
    }

    private static void menu4(Branch branch) throws SQLException {
        System.out.println("You have chosen to update the Branch with Branch Id: " + branch.getBranchId() + " and Branch Name: " + branch.getBranchName() + ".");
        System.out.println("Enter \"quit\" at any prompt to return to the previous menu.\n");

        //Input.getString();

        System.out.print("Please enter a new branch name or enter N/A for no change: ");
        String name = Input.getString();
        System.out.println(name);

        if (name.equalsIgnoreCase("quit")) {
            System.out.println("Returning to previous menu.\n");
            menu3(branch);
        }

        System.out.print("Please enter a new branch address or enter N/A for no change: ");
        String address = Input.getString();

        if (address.equalsIgnoreCase("quit")) {
            System.out.println("Returning to previous menu.\n");
            menu3(branch);
        }

        if (!name.equalsIgnoreCase("n/a"))
            branch.setBranchName(name);
        if (!address.equalsIgnoreCase("n/a"))
            branch.setBranchAddress(address);

        branchDao.update(branch);
        System.out.println("This branch has been successfully updated.\n");
        menu3(branch);
    }

    private static void menu5(Branch branch, int page) throws SQLException {
        List<Book> books = bookDao.getAll();

        int i = (page - 1) * 25 + 1;
        while (i <= page * 25) {
            Book book = books.get(i-1);
            Author author = authorDao.getSingle(book.getAuthId());
            System.out.println(i + ") " + book.getTitle() + " by " + author.getAuthorName());
            i++;
        }

        System.out.println(i + ") Cancel Operation");
        if ((page + 1) * 25 < books.size())
            System.out.println((i+1) + ") Next Page of Books");
        if (page > 1)
            System.out.println((i+2) + ") Previous Page of Books");
        System.out.println();

        int option = Input.getInt((page - 1) * 25 + 1, page * 25 + 3);
        System.out.println();

        if (option == i) {
            menu3(branch);
        } else if (option == i+1) {
            if ((page + 1) * 25 < books.size()) {
                menu5(branch, page + 1);
            } else {
                System.out.println("Please enter a valid input.");
                menu5(branch, page);
            }
        } else if (option == i+2) {
            if (page > 1) {
                menu5(branch, page - 1);
            } else {
                System.out.println("Please enter a valid input.");
                menu5(branch, page);
            }
        }

        BookCopy copies = copyDao.getSingle(books.get(option-1).getBookId(), branch.getBranchId());
        boolean newLine = false;

        if (copies == null) {
            copies = new BookCopy(books.get(option - 1).getBookId(), branch.getBranchId(), 0);
            newLine = true;
        }

        System.out.println("Existing Number of Copies of " + books.get(option-1).getTitle() +": " + copies.getNoOfCopies());
        System.out.print("Enter a New Number of Copies: ");
        copies.setNoOfCopies(Input.getInt(0, 10000));

        if (newLine)
            copyDao.add(copies);
        else
            copyDao.update(copies);
    }
}
