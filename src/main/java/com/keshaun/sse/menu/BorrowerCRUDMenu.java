package com.keshaun.sse.menu;

import com.keshaun.sse.util.Input;

import java.sql.SQLException;

public class BorrowerCRUDMenu {
    public static void mainMenu() throws SQLException {
        System.out.println("1) Add a New Borrower");
        System.out.println("2) List Borrowers");
        System.out.println("3) Update a Borrower");
        System.out.println("4) Delete a Borrower");
        System.out.println("5) Return to Previous Menu\n");

        int option = Input.getInt(1, 5);
        System.out.println();

        switch (option) {
            case 1 -> createBorrower1();
            case 2 -> readBorrower1();
            case 3 -> updateBorrower1();
            case 4 -> destroyBorrower1();
            default -> AdminMenu.mainMenu();
        }
    }

    private static void createBorrower1() {}

    private static void readBorrower1() {}

    private static void updateBorrower1() {}

    private static void destroyBorrower1() {}
}
