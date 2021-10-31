package com.keshaun.sse.menu;

import com.keshaun.sse.dao.BorrowerDao;
import com.keshaun.sse.model.Borrower;
import com.keshaun.sse.util.Input;

import java.sql.SQLException;

public class BorrowerMenu {
    private final static BorrowerDao borrowerDao = new BorrowerDao();

    public static void mainMenu() throws SQLException {
        System.out.println("Please enter your Card Number: ");
        Borrower borrower = null;

        while (borrower == null) {
            int cardNo = Input.getCardNo();
            Borrower temp = borrowerDao.getSingle(cardNo);
            if (temp == null)
                System.out.println("The provided Card Number is not in the system. Please try again.");
            borrower = temp;
        }

        System.out.println();
        menu2(borrower);
    }

    private static void menu2(Borrower borrower) throws SQLException {
        System.out.println("Welcome, " + borrower.getName() + ". Please select an option below:\n");

        System.out.println("1) Check Out a Book");
        System.out.println("2) Return a Book");
        System.out.println("3) Quit to Previous Menu");

        int option = Input.getInt(1, 3);
        System.out.println();

        switch(option) {
            case 1 -> menu3(borrower);
            case 2 -> menu4(borrower);
            default -> Main.mainMenu();
        }
    }

    private static void menu3(Borrower borrower) {}

    private static void menu4(Borrower borrower) {}
}
