package com.keshaun.sse.menu;

import com.keshaun.sse.util.Input;

import java.sql.SQLException;

public class AdminMenu {
    public static void mainMenu() throws SQLException {
        System.out.println("Welcome, Administrator. Please select an option below:\n");

        System.out.println("1) Add/Read/Update/Delete Books");
        System.out.println("2) Add/Read/Update/Delete Authors");
        System.out.println("3) Add/Read/Update/Delete Publishers");
        System.out.println("4) Add/Read/Update/Delete Library Branches");
        System.out.println("5) Add/Read/Update/Delete Borrowers");
        System.out.println("6) Over-ride Due Date for a Book Loan");
        System.out.println("7) Return to Previous Menu\n");

        int option = Input.getInt(1, 7);
        System.out.println();

        switch (option) {
            case 1 -> BookCRUDMenu.mainMenu();
            case 2 -> AuthorCRUDMenu.mainMenu();
            case 3 -> PublisherCRUDMenu.mainMenu();
            case 4 -> BranchCRUDMenu.mainMenu();
            case 5 -> BorrowerCRUDMenu.mainMenu();
            case 6 -> BookLoanOverrideMenu.mainMenu();
            default -> Main.mainMenu();
        }
    }
}
