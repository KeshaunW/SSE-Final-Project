package com.keshaun.sse;

import com.keshaun.sse.menu.AdminMenu;
import com.keshaun.sse.menu.BorrowerMenu;
import com.keshaun.sse.menu.LibrarianMenu;
import com.keshaun.sse.util.Input;

public class Main {
    public static void mainMenu() {
        System.out.println("Welcome to the Smoothstack Library Management System. Which category of user are you:\n");

        System.out.println("1) Librarian");
        System.out.println("2) Administrator");
        System.out.println("3) Borrower\n");

        int user = Input.getInt(1, 3);

        switch(user) {
            case 1 -> LibrarianMenu.mainMenu();
            case 2 -> AdminMenu.mainMenu();
            default -> BorrowerMenu.mainMenu();
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }
}
