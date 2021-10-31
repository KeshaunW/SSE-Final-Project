package com.keshaun.sse.menu;

import com.keshaun.sse.util.Input;

import java.sql.SQLException;

public class Main {
    public static void mainMenu() throws SQLException {
        System.out.println("Welcome to the Smoothstack Library Management System. Which category of user are you:\n");

        System.out.println("1) Librarian");
        System.out.println("2) Administrator");
        System.out.println("3) Borrower\n");

        int user = Input.getInt(1, 3);
        System.out.println();

        switch(user) {
            case 1 -> LibrarianMenu.mainMenu();
            case 2 -> AdminMenu.mainMenu();
            default -> BorrowerMenu.mainMenu();
        }
    }

    public static void main(String[] args) throws SQLException {
        mainMenu();
    }
}
