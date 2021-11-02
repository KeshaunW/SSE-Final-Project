package com.keshaun.sse.menu;

import com.keshaun.sse.util.Input;

import java.sql.SQLException;

public class BookCRUDMenu {
    public static void mainMenu() throws SQLException {
        System.out.println("1) Add a New Book");
        System.out.println("2) List Books");
        System.out.println("3) Update a Book");
        System.out.println("4) Delete a Book");
        System.out.println("5) Return to Previous Menu\n");

        int option = Input.getInt(1, 5);
        System.out.println();

        switch (option) {
            case 1 -> createBook1();
            case 2 -> readBook1();
            case 3 -> updateBook1();
            case 4 -> destroyBook1();
            default -> AdminMenu.mainMenu();
        }
    }

    private static void createBook1() {}

    private static void readBook1() {}

    private static void updateBook1() {}

    private static void destroyBook1() {}
}
