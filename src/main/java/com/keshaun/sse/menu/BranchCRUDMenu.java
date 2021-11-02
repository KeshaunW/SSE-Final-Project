package com.keshaun.sse.menu;

import com.keshaun.sse.util.Input;

import java.sql.SQLException;

public class BranchCRUDMenu {
    public static void mainMenu() throws SQLException {
        System.out.println("1) Add a New Branch");
        System.out.println("2) List Branches");
        System.out.println("3) Update a Branch");
        System.out.println("4) Delete a Branch");
        System.out.println("5) Return to Previous Menu\n");

        int option = Input.getInt(1, 5);
        System.out.println();

        switch (option) {
            case 1 -> createBranch1();
            case 2 -> readBranch1();
            case 3 -> updateBranch1();
            case 4 -> destroyBranch1();
            default -> AdminMenu.mainMenu();
        }
    }

    private static void createBranch1() {}

    private static void readBranch1() {}

    private static void updateBranch1() {}

    private static void destroyBranch1() {}
}
