package com.keshaun.sse.menu;

import com.keshaun.sse.util.Input;

import java.sql.SQLException;

public class AuthorCRUDMenu {
    public static void mainMenu() throws SQLException {
        System.out.println("1) Add a New Author");
        System.out.println("2) List Authors");
        System.out.println("3) Update a Author");
        System.out.println("4) Delete a Author");
        System.out.println("5) Return to Previous Menu\n");

        int option = Input.getInt(1, 5);
        System.out.println();

        switch (option) {
            case 1 -> createAuthor1();
            case 2 -> readAuthor1();
            case 3 -> updateAuthor1();
            case 4 -> destroyAuthor1();
            default -> AdminMenu.mainMenu();
        }
    }

    private static void createAuthor1() {}

    private static void readAuthor1() {}

    private static void updateAuthor1() {}

    private static void destroyAuthor1() {}
}
