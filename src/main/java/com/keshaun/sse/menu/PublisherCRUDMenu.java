package com.keshaun.sse.menu;

import com.keshaun.sse.util.Input;

import java.sql.SQLException;

public class PublisherCRUDMenu {
    public static void mainMenu() throws SQLException {
        System.out.println("1) Add a New Publisher");
        System.out.println("2) List Publishers");
        System.out.println("3) Update a Publisher");
        System.out.println("4) Delete a Publisher");
        System.out.println("5) Return to Previous Menu\n");

        int option = Input.getInt(1, 5);
        System.out.println();

        switch (option) {
            case 1 -> createPublisher1();
            case 2 -> readPublisher1();
            case 3 -> updatePublisher1();
            case 4 -> destroyPublisher1();
            default -> AdminMenu.mainMenu();
        }
    }

    private static void createPublisher1() {}

    private static void readPublisher1() {}

    private static void updatePublisher1() {}

    private static void destroyPublisher1() {}
}
