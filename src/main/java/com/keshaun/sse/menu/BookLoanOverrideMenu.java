package com.keshaun.sse.menu;

import com.keshaun.sse.util.Input;

import java.sql.SQLException;

public class BookLoanOverrideMenu {
    public static void mainMenu() throws SQLException {
        System.out.println("1) Add a New BookLoan");
        System.out.println("2) List BookLoans");
        System.out.println("3) Update a BookLoan");
        System.out.println("4) Delete a BookLoan");
        System.out.println("5) Return to Previous Menu\n");

        int option = Input.getInt(1, 5);
        System.out.println();

        switch (option) {
            case 1 -> createBookLoan1();
            case 2 -> readBookLoan1();
            case 3 -> updateBookLoan1();
            case 4 -> destroyBookLoan1();
            default -> AdminMenu.mainMenu();
        }
    }

    private static void createBookLoan1() {}

    private static void readBookLoan1() {}

    private static void updateBookLoan1() {}

    private static void destroyBookLoan1() {}
}
